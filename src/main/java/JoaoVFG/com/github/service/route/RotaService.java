package JoaoVFG.com.github.service.route;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.ListaEnderecoRotaDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.entity.Rota;
import JoaoVFG.com.github.entity.security.User;
import JoaoVFG.com.github.repositories.RotaRepository;
import JoaoVFG.com.github.service.security.UserService;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;



@Service
public class RotaService {

	@Autowired
	private GeraRota geraRota;
	
	@Autowired
	private RotaRepository rotaRepository;
	
	@Autowired
	private UserService userService;
	
	
	public RotaResponseDTO geraRotaRespose(ListaEnderecoRotaDTO listaEnderecoRotaDTO) {
		User user = userService.findById(listaEnderecoRotaDTO.getIdUser());

		RotaResponseDTO rotaResponseDTO = geraRota.geraRota(user, listaEnderecoRotaDTO.getWaypoints());
		
		return rotaResponseDTO;	
	}
	
	public Rota findById(Integer id){
		Optional<Rota> rota = rotaRepository.findById(id);
		return rota.orElseThrow(() -> new ObjectNotFoundException("ROTA NÃO ENCONTRADA. ID: " + id + "Tipo: " + Rota.class.getName()));
	}
}
