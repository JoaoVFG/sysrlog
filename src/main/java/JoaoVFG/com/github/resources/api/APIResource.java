package JoaoVFG.com.github.resources.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JoaoVFG.com.github.dto.request.ListaEnderecoRotaDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.service.route.RotaService;

@RestController
@RequestMapping(value = "/api")
public class APIResource {

	@Autowired
	private RotaService rotaService;

	@RequestMapping(method = RequestMethod.POST, value = "/gerarota/{apiKey}")
	public ResponseEntity<RotaResponseDTO> gerarRota(@PathVariable("apiKey") String apiKey,
			@RequestBody ListaEnderecoRotaDTO listaEnderecoRotaDTO) {
		RotaResponseDTO rotaResponseDTO = rotaService.geraRotaReponseByApiKey(listaEnderecoRotaDTO, apiKey);
		return ResponseEntity.ok().body(rotaResponseDTO);
	}
}
