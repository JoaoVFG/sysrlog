package JoaoVFG.com.github.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import JoaoVFG.com.github.dto.request.ListaEnderecoRotaDTOwithUser;
import JoaoVFG.com.github.dto.response.RotaBuscaResponseDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.service.route.RotaService;

@RestController
@RequestMapping(value = "/rota")
public class RotaResource {

	@Autowired
	private RotaService rotaService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.POST, value = "/criarotajson")
	public ResponseEntity<RotaResponseDTO> gerarRota(@RequestBody ListaEnderecoRotaDTOwithUser listaEnderecoRotaDTOwithUser){
		RotaResponseDTO rotaResponseDTO = rotaService.geraRotaRespose(listaEnderecoRotaDTOwithUser);
		return ResponseEntity.ok().body(rotaResponseDTO);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET, value = "/busca/id/{id}")
	public ResponseEntity<RotaBuscaResponseDTO> findById(@PathVariable("id") Integer id){
		RotaBuscaResponseDTO rotaBuscaResponseDTO = rotaService.findByid(id);
		return ResponseEntity.ok().body(rotaBuscaResponseDTO);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET, value = "/busca/{idUser}")
	public ResponseEntity<List<RotaBuscaResponseDTO>> findALL(@PathVariable("idUser") Integer idUser){
		List<RotaBuscaResponseDTO> rotaBuscaResponseDTOs = rotaService.findAllByEmpresaOrPessoa(idUser);
		return ResponseEntity.ok().body(rotaBuscaResponseDTOs);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Void> deleteROtaById(@PathVariable("id") Integer id){
		rotaService.deleteRota(id);
		return ResponseEntity.noContent().build();
	}
}
