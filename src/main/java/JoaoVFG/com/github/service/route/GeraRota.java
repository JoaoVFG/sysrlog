package JoaoVFG.com.github.service.route;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.dto.response.ResponsavelRegiaoDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.entity.Empresa;
import JoaoVFG.com.github.entity.Endereco;
import JoaoVFG.com.github.entity.Funcionario;
import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.Regiao;
import JoaoVFG.com.github.repositories.FuncionarioRepository;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.EmpresaService;
import JoaoVFG.com.github.service.EnderecoService;
import JoaoVFG.com.github.service.RegiaoService;

@Service
public class GeraRota {

	@Autowired
	private CalculaDistancia calculaDistancia;

	@Autowired
	private CepService cepService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private RegiaoService regiaoService;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaService empresaService;

	public RotaResponseDTO geraRota(Pessoa pessoa, List<EnderecoEntregaDTO> enderecoEntregaDTOs) {

		// objeto de retorno da função - INICIALZIAÇÃO
		RotaResponseDTO rotaResponseDTO = new RotaResponseDTO();

		/**
		 * objeto de Funcionario - INICIALZIAÇÃO Necessario para verificar se a pessoa é
		 * vinculada a alguma empresa
		 */
		Funcionario funcionario = funcionarioRepository.findBypessoa(pessoa);

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
		 * Verifica se a pessoa é funcionario de alguma empresa Se for, ira trazer de
		 * qual empresa a pessoa é funcionaria
		 */
		if (!(funcionario == null)) {
			empresa = empresaService.findById(funcionario.getEmpresa().getId());
		}

		/**
		 * Se a empresa permanecer for nulo, quer dizer que o usuário que solicitou a
		 * rota não é o funcionario de nenhuma empresa então usará seu endereço como
		 * ponto de partida
		 */
		if (!(empresa.getId() == null)) {

			// Lista para os Ceps que a empresa não atende - INICIALZIAÇÃO
			List<ResponsavelRegiaoDTO> listResponsavelRegiao = new ArrayList<>();

			// Regiao de atuação de empresa
			Regiao regiao = regiaoService.findByEmpresa(empresa.getId());

			// Regiões das empresas parceiras
			List<Regiao> regioesBusca = regiaoService.findByEmpresaMatriz(empresa.getEmpresaMatrizId());

			// verifica se a empresa tem região de atuação
			if (!regiao.equals(null)) {
				/**
				 * Se tiver uma regiao de atuação, ira iterar pela lista dos endereços pra
				 * verficar se ela faz parte da sua area de atuação
				 */
				for (int i = 0; i < enderecoEntregaDTOs.size(); i++) {

					// verifica se o endereço de entrega esta na lista de atuação
					if (!regiao.getCeps().contains(cepService.findByCep(enderecoEntregaDTOs.get(i).getCep()))) {

						// caso não esteja
						// busca se tem uma empresa da mesma cadeia que entregue nesse cep

						for (Regiao r : regioesBusca) {

							// Se dentro da região contiver um responsável pelo CEP
							if (r.getCeps().contains(cepService.findByCep(enderecoEntregaDTOs.get(i).getCep()))) {

								// adicioa o CEP e a empresa responsavel a listaResponsavelRegiao
								listResponsavelRegiao.add(new ResponsavelRegiaoDTO(enderecoEntregaDTOs.get(i).getCep(),
										r.getEmpresa().getPessoa().getRazaoSocial()));
								break;
							}
						}

						// remove endereço da lista a ser roteirizada
						enderecoEntregaDTOs.remove(i);

					}
				}
			}

			endereco = enderecoService.findByPessoa(empresa.getPessoa().getId());
			rotaResponseDTO.setResponsavel(listResponsavelRegiao);

		}
		// Atribui o endereço da pessoa do usuário ao endereço de partida
		else {
			endereco = enderecoService.findByPessoa(pessoa.getId());
		}

		// Lista não roteirizada -> ja exclusos os endereços que a empresa não entrega
		List<String> listaEnderecosStringNaoRoteirizado = enderecoClienteDtoToString(enderecoEntregaDTOs);

		String enderecoPartida = cepService.cepToStringEndereco(endereco.getCep().getCep(),
				endereco.getNumeroLogradouro().toString());
		String enderecoSalvo = enderecoPartida;

		rotaResponseDTO.setEnderecoOrigem(enderecoPartida);

		List<String> listaRoteirizada = new ArrayList<String>();

		while (!listaEnderecosStringNaoRoteirizado.isEmpty()) {
			// recebe o ponto mais proximo de enderecoString
			enderecoPartida = calculaDistancia.findMenorDistancia(enderecoPartida, listaEnderecosStringNaoRoteirizado);
			listaRoteirizada.add(enderecoPartida);
			listaEnderecosStringNaoRoteirizado.remove(enderecoPartida);
		}

		rotaResponseDTO.setWaypoints(listaRoteirizada);

		// return geraUrlMaps(listaRoteirizada, enderecoEmpresa);
		rotaResponseDTO.setRota(geraUrlMaps(listaRoteirizada, enderecoSalvo));
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

			builder.append("&travelmode=driving");
		} catch (UnsupportedEncodingException e1) {
			return null;
		}

		System.out.println(builder.toString());
		return builder.toString();

	}

	public List<String> enderecoClienteDtoToString(List<EnderecoEntregaDTO> enderecos) {
		List<String> enderecosString = new ArrayList<String>();

		for (EnderecoEntregaDTO e : enderecos) {
			enderecosString.add(cepService.cepToStringEndereco(e.getCep(), e.getNumeroLogradouro()));
		}

		return enderecosString;
	}

}
