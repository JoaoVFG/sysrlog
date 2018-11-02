package JoaoVFG.com.github.service.route;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.dto.response.ResponsavelRegiaoDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.entity.Empresa;
import JoaoVFG.com.github.entity.Endereco;
import JoaoVFG.com.github.entity.Funcionario;
import JoaoVFG.com.github.entity.Regiao;
import JoaoVFG.com.github.entity.ResponsavelEntregaCepRota;
import JoaoVFG.com.github.entity.Rota;
import JoaoVFG.com.github.entity.RotaEndereco;
import JoaoVFG.com.github.entity.security.User;
import JoaoVFG.com.github.repositories.FuncionarioRepository;
import JoaoVFG.com.github.repositories.ResponsavelEntregaRepository;
import JoaoVFG.com.github.repositories.RotaEnderecoRepository;
import JoaoVFG.com.github.repositories.RotaRepository;
import JoaoVFG.com.github.security.ListasEnderecos;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.EmpresaService;
import JoaoVFG.com.github.service.EnderecoService;
import JoaoVFG.com.github.service.RegiaoService;

@Service
public class GeraRota {


	@Autowired
	private CalculaDistancia calculaDistancia;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private RotaRepository rotaRepository;

	@Autowired
	private RotaEnderecoRepository rotaEnderecoRepository;

	@Autowired
	private ResponsavelEntregaRepository responsavelEntregaRepository;

	@Autowired
	private CepService cepService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private RegiaoService regiaoService;

	@Autowired
	private EmpresaService empresaService;

	@Transactional()
	public RotaResponseDTO geraRota(User user, List<EnderecoEntregaDTO> enderecoEntregaDTOs) {

		// objeto de retorno da função - INICIALZIAÇÃO
		RotaResponseDTO rotaResponseDTO = new RotaResponseDTO();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		// Objeto para armazenar rota
		Rota rota = new Rota(null, dateFormat.format(date), "", user);
		rota = rotaRepository.save(rota);
		
		/**
		 *Objeto para Lista de Endereços A serem roteirizadas e as que não serão roteirizadas
		 */
		ListasEnderecos listasEnderecos = new ListasEnderecos();
		/**
		 * objeto de Funcionario - INICIALZIAÇÃO Necessario para verificar se a pessoa é
		 * vinculada a alguma empresa
		 */
		Funcionario funcionario = funcionarioRepository.findBypessoa(user.getPessoa());

		/**
		 * objeto de Empresa - INICIALZIAÇÃO Caso a pessoa que foi passada seja
		 * funcionario de alguma empresa no ato de criar a rota ela usará os parametros
		 * de regiões cadastrados pela empresa
		 * 
		 */
		Empresa empresa = new Empresa();

		/**
		 * objeto de Endereco - INICIALZIAÇÃO Referente ao endereço inicial -> se for
		 * pessoa usara seu endereço proprio -> se for funcionario usará endereço da
		 * empresa
		 */
		Endereco endereco = new Endereco();
		/**
		 * Verifica se a pessoa é fisica ou juridica
		 */
		if (user.getPessoa().getTipo().getId() == 1) {
			/**
			 * Verifica se a pessoa é funcionario de alguma empresa Se for, ira trazer de
			 * qual empresa a pessoa é funcionaria
			 */
			if (!(funcionario == null)) {
				empresa = empresaService.findById(funcionario.getEmpresa().getId());
				rota.setEmpresa(empresa);
			}
			/**
			 * Se for Juridica atribui ao objeto empresa a empresa vinculada a pessoa do
			 * usuário
			 */
		} else {
			empresa = empresaService.findByIdPessoa(user.getPessoa().getId());

		}

		/**
		 * Se a pessoa do usuário que solicitou a rota for funcionário de alguma empresa
		 * caira no irá verificar se a empresa possui região de atuação, caso possua irá
		 * verificar se ela atende todos os ceps passados e caso não atende irá
		 * identificar as filias que atender(IF)
		 * 
		 * Se a empresa permanecer for nulo, quer dizer que o usuário que solicitou a
		 * rota não é o funcionario de nenhuma empresa então usará seu endereço como
		 * ponto de partida(ELSE)
		 */
		if (!(empresa.getId() == null)) {

			// Regiao de atuação de empresa
			Regiao regiao = regiaoService.findByEmpresa(empresa.getId());
			// verifica se a empresa tem região de atuação
			if (!(regiao == null)) {
				listasEnderecos = buscaResponsavelEntraga(empresa, enderecoEntregaDTOs, regiao, rota);
				rotaResponseDTO.setResponsavel(listasEnderecos.getResponsavelRegiaoDTO());
				enderecoEntregaDTOs = listasEnderecos.getEnderecoEntregaDTOs();
			}
			endereco = enderecoService.findByPessoa(empresa.getPessoa().getId());

		}
		// Atribui o endereço da pessoa do usuário ao endereço de partida
		else {
			endereco = enderecoService.findByPessoa(user.getPessoa().getId());
		}

		
		EnderecoEntregaDTO enderecoPartida = new EnderecoEntregaDTO(endereco.getCep().getCep(), endereco.getNumeroLogradouro().toString());
				
		EnderecoEntregaDTO enderecoSalvo = enderecoPartida;
		enderecoSalvo.setEnderecoString(cepService.cepToStringEndereco(enderecoSalvo.getCep(), enderecoSalvo.getNumeroLogradouro()));

		rotaResponseDTO.setEnderecoOrigem(cepService.cepToStringEndereco(enderecoPartida.getCep(), enderecoPartida.getNumeroLogradouro()));

		List<String> listaRoteirizada = new ArrayList<String>();
		enderecoEntregaDTOs = enderecoClienteDtoToString(enderecoEntregaDTOs);
		
		while (!enderecoEntregaDTOs.isEmpty()) {
			// recebe o ponto mais proximo de enderecoString
			enderecoPartida = calculaDistancia.findMenorDistancia(cepService.cepToStringEndereco(enderecoPartida.getCep(), enderecoPartida.getNumeroLogradouro()), enderecoEntregaDTOs);
			listaRoteirizada.add(enderecoPartida.getEnderecoString());
			armazenaEnderecoRota(enderecoPartida, rota);
			enderecoEntregaDTOs.remove(enderecoPartida);
		}

		rotaResponseDTO.setWaypoints(listaRoteirizada);

		// return geraUrlMaps(listaRoteirizada, enderecoEmpresa);
		String url = geraUrlMaps(listaRoteirizada, enderecoSalvo.getEnderecoString());

		rotaResponseDTO.setRota(url);
		rota.setUrlRota(url);
		rotaRepository.save(rota);
		return rotaResponseDTO;
	}

	// Método responsável por gerar a URL do google maps a partir de um endereco
	public String geraUrlMaps(List<String> listaRota, String enderecoSalvo) {

		StringBuilder builder = new StringBuilder();

		String sufixo = "https://www.google.com/maps/dir/?api=1";
		builder.append(sufixo);

		try {

			builder.append("&origin=");
			builder.append(URLEncoder.encode(enderecoSalvo, "UTF-8"));
			builder.append("&destination=");
			builder.append(URLEncoder.encode(enderecoSalvo, "UTF-8"));
			builder.append("&waypoints=");
			for (String e : listaRota) {
				builder.append(URLEncoder.encode(e, "UTF-8"));
				builder.append("|");
			}

			builder.append("&travelmode=driving&key=AIzaSyBxdBRcPlju7HOIIDR8HUhWdHtjkXaUdD4");
		} catch (UnsupportedEncodingException e1) {
			return null;
		}

		return builder.toString();

	}

	private void armazenaResponsavelEntrega(String cep, Empresa empresa, Rota rota) {
		ResponsavelEntregaCepRota responsavelEntregaCepRota = new ResponsavelEntregaCepRota(null,
				cepService.findByCep(cep), empresa, rota);
		responsavelEntregaRepository.save(responsavelEntregaCepRota);
	}

	private void armazenaEnderecoRota(EnderecoEntregaDTO enderecoEntregaDTO, Rota rota) {

		Integer num = null;

		if (!(enderecoEntregaDTO.getNumeroLogradouro() == "")) {
			num = Integer.parseInt(enderecoEntregaDTO.getNumeroLogradouro());
		}
		Endereco endereco = enderecoService
				.create(new Endereco(null, null, cepService.findByCep(enderecoEntregaDTO.getCep()), num, null));

		rotaEnderecoRepository.save(new RotaEndereco(null, endereco, rota));
	}

	private List<EnderecoEntregaDTO> enderecoClienteDtoToString(List<EnderecoEntregaDTO> enderecos) {


		for (EnderecoEntregaDTO e : enderecos) {
			e.setEnderecoString(cepService.cepToStringEndereco(e.getCep(), e.getNumeroLogradouro()));
		}

		return enderecos;
	}

	
	private ListasEnderecos buscaResponsavelEntraga(Empresa empresa,
			List<EnderecoEntregaDTO> enderecoEntregaDTOs, Regiao regiao, Rota rota) {
		
		
		// Lista para os Ceps que a empresa não atende - INICIALZIAÇÃO
		List<ResponsavelRegiaoDTO> listResponsavelRegiao = new ArrayList<>();
		
		// Lista para os endereços que empresa atende
		List<EnderecoEntregaDTO> newEnderecoEntregaDTOs = new ArrayList<>();
		
		//achar id da empresa matriz
		Integer idMatriz = retornaIdMatriz(empresa);
		// Regiões das empresas parceiras
		List<Regiao> regioesBusca = regiaoService.findByEmpresaMatriz(idMatriz);

		/**
		 * Se tiver uma regiao de atuação, ira iterar pela lista dos endereços pra
		 * verficar se ela faz parte da sua area de atuação
		 */
		for (EnderecoEntregaDTO e : enderecoEntregaDTOs) {
			// verifica se o endereço de entrega esta na lista de atuação
			if (!regiao.getCeps().contains(cepService.findByCep(e.getCep()))) {

				// caso não esteja
				// busca se tem uma empresa da mesma cadeia que entregue nesse cep

				for (Regiao r : regioesBusca) {

					// Se dentro da região contiver um responsável pelo CEP
					if (r.getCeps().contains(cepService.findByCep(e.getCep()))) {
						// Salva o Responsável pela Entrega no Banco De Dados
						armazenaResponsavelEntrega(e.getCep(), r.getEmpresa(), rota);
						// adicioa o CEP e a empresa responsavel a listaResponsavelRegiao
						listResponsavelRegiao
								.add(new ResponsavelRegiaoDTO(e.getCep(), r.getEmpresa().getPessoa().getRazaoSocial()));
						// remove endereço da lista a ser roteirizada
						
						break;
					}

				}

			}else {
				newEnderecoEntregaDTOs.add(e);
			}

		}

		return new ListasEnderecos(listResponsavelRegiao, newEnderecoEntregaDTOs); 

	}
	
	
	

	private Integer retornaIdMatriz(Empresa empresa) {
		if (empresa.getTipoEmpresa().getId() == 1) {
			return empresa.getPessoa().getId();
		} else {
			return empresa.getEmpresaMatrizId();
		}

	}

}
