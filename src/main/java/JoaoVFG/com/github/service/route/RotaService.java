package JoaoVFG.com.github.service.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.ListaEnderecoRotaDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.entity.security.User;
import JoaoVFG.com.github.service.security.UserService;



@Service
public class RotaService {

	@Autowired
	private GeraRota geraRota;
	
	@Autowired
	private UserService userService;
	
	
	public RotaResponseDTO geraRotaRespose(ListaEnderecoRotaDTO listaEnderecoRotaDTO) {
		User user = userService.findById(listaEnderecoRotaDTO.getIdUser());

		RotaResponseDTO rotaResponseDTO = geraRota.geraRota(user.getPessoa(), listaEnderecoRotaDTO.getWaypoints());
		
		return rotaResponseDTO;	
	}
}
