package JoaoVFG.com.github.service.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.ListaEnderecoRotaDTO;
import JoaoVFG.com.github.dto.response.RotaBuscaResponseDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.entity.Funcionario;
import JoaoVFG.com.github.entity.ResponsavelEntregaCepRota;
import JoaoVFG.com.github.entity.Rota;
import JoaoVFG.com.github.entity.RotaEndereco;
import JoaoVFG.com.github.entity.security.User;
import JoaoVFG.com.github.repositories.RotaEnderecoRepository;
import JoaoVFG.com.github.repositories.RotaRepository;
import JoaoVFG.com.github.service.FuncionarioService;
import JoaoVFG.com.github.service.security.UserService;
import JoaoVFG.com.github.services.exception.DataIntegrityException;

@Service
public class RotaService {

	@Autowired
	private GeraRota geraRota;

	@Autowired
	private RotaRepository rotaRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FuncionarioService funcionarioService;


	@Autowired
	private ResponsavelEntregaService responsavelEntregaService;

	@Autowired
	private RotaEnderecoRepository rotaEnderecoRepository;

	public RotaResponseDTO geraRotaRespose(ListaEnderecoRotaDTO listaEnderecoRotaDTO) {
		User user = userService.findById(listaEnderecoRotaDTO.getIdUser());

		RotaResponseDTO rotaResponseDTO = geraRota.geraRota(user, listaEnderecoRotaDTO.getWaypoints());

		return rotaResponseDTO;
	}

	/**
	 * public Rota findById(Integer id){ Optional<Rota> rota =
	 * rotaRepository.findById(id); return rota.orElseThrow(() -> new
	 * ObjectNotFoundException("ROTA NÃO ENCONTRADA. ID: " + id + "Tipo: " +
	 * Rota.class.getName())); }
	 **/

	public RotaBuscaResponseDTO findByid(Integer id) {
		Optional<Rota> rota = rotaRepository.findById(id);

		List<RotaEndereco> rotaEnderecos = rotaEnderecoRepository.findByRotaId(rota.get().getId());

		List<ResponsavelEntregaCepRota> responsavelEntregaCepRotas = responsavelEntregaService
				.findByIdRota(rota.get().getId());

		RotaBuscaResponseDTO rotaBuscaResponseDTO = new RotaBuscaResponseDTO(rota.get(), rotaEnderecos,
				responsavelEntregaCepRotas);

		return rotaBuscaResponseDTO;
	}

	public List<RotaBuscaResponseDTO> findAllByEmpresaOrPessoa(Integer idUser) {
		List<Rota> rotas;
		User user = userService.findById(idUser);
		Funcionario funcionario = funcionarioService.findByPessoa(user.getPessoa().getId());

		if (funcionario == null) {
			rotas = rotaRepository.findByIdUser(idUser);
		} else {
			rotas = rotaRepository.findByIdEmpresa(funcionario.getEmpresa().getId());
		}

		List<RotaBuscaResponseDTO> rotaBuscaResponseDTOs = new ArrayList<RotaBuscaResponseDTO>();

		for (Rota r : rotas) {
			rotaBuscaResponseDTOs.add(new RotaBuscaResponseDTO(r, rotaEnderecoRepository.findByRotaId(r.getId()),
					responsavelEntregaService.findByIdRota(r.getId())));
		}
		
		return rotaBuscaResponseDTOs;

	}

	
	public void deleteRota(Integer idRota) {
		rotaRepository.findById(idRota);
		try {
			rotaRepository.deleteById(idRota);
		}catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSA PESSOA.");
		}
	}
	
}
