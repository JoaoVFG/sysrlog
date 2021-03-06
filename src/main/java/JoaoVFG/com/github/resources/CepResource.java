package JoaoVFG.com.github.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JoaoVFG.com.github.dto.response.CepResponseDTO;
import JoaoVFG.com.github.entity.Cep;
import JoaoVFG.com.github.entity.Cidade;
import JoaoVFG.com.github.entity.Estado;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.CidadeService;
import JoaoVFG.com.github.service.EstadoService;

@RestController
@RequestMapping(value = "/ceps")
public class CepResource {

	@Autowired
	CepService cepService;

	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	EstadoService estadoService;

	@RequestMapping(value = "/buscacep/{cepBusca}", method = RequestMethod.GET)
	public ResponseEntity<CepResponseDTO> findByCep(@PathVariable String cepBusca) {
		Cep cep = cepService.findByCep(cepBusca);
		return ResponseEntity.ok().body(cepService.cepToCepDTO(cep));
	}

	@RequestMapping(value = "/buscacep", method = RequestMethod.GET)
	public ResponseEntity<List<CepResponseDTO>> findAll() {
		List<Cep> ceps = cepService.findAll();
		return ResponseEntity.ok().body(cepService.listCepToListCepDTO(ceps));
	}

	@RequestMapping(value = "/buscacep/estado/{estado}/cidade/{cidade}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByCidade(@PathVariable String cidade, @PathVariable String estado) {
		List<Cep> ceps = cepService.findByCidade(cidade, estado);
		return ResponseEntity.ok().body(ceps);
	}

	@RequestMapping(value = "/buscacep/rua/{nomeRua}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByNomeRua(@PathVariable String nomeRua) {
		List<Cep> ceps = cepService.findByNomeRua(nomeRua);
		return ResponseEntity.ok().body(ceps);
	}

	@RequestMapping(value = "/buscacep/cidade/{cidade}/bairro/{bairro}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByCidadeAndBairro(@PathVariable String cidade, @PathVariable String bairro) {
		List<Cep> ceps = cepService.findByBairroAndCidade(bairro, cidade);
		return ResponseEntity.ok(ceps);
	}

	@RequestMapping(value = "/buscacep/cidadeEstado/{estadoSigla}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByEstado(@PathVariable String estadoSigla) {
		List<Cidade> cidades = cidadeService.findByEstado(estadoSigla);
		return ResponseEntity.ok().body(cidades);
	}
	
	@RequestMapping(value = "/buscacep/estados", method = RequestMethod.GET)
	public ResponseEntity<List<Estado>> findAllEstador(){
		List<Estado> estados = estadoService.findAllEstados();
		return ResponseEntity.ok().body(estados);	
	}

}
