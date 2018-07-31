package JoaoVFG.com.github.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.service.PessoaService;

@RestController
@RequestMapping(value="/pessoa")
public class PessoaResource {
	
	@Autowired
	PessoaService pessoaService;
	
	@RequestMapping(value="/buscaPessoa", method = RequestMethod.GET)	
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> pessoas = pessoaService.findAll();
		return ResponseEntity.ok().body(pessoas);
	}
	

	@RequestMapping(value="/buscaPessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findById(@PathVariable String id){
		Pessoa pessoa = pessoaService.findById(Integer.parseInt(id));
		return ResponseEntity.ok().body(pessoa);
	}
	
	@RequestMapping(value="/buscaPessoa/tipo/{tipo}", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findByTipo(@PathVariable String tipo){
		List<Pessoa> pessoas = pessoaService.findByTipo(Integer.parseInt(tipo));
		return ResponseEntity.ok().body(pessoas);
	}
	
	@RequestMapping(value="/buscaPessoa/cpf/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findByCpf(@PathVariable String cpf){
		Pessoa pessoa = pessoaService.findByCpf(cpf);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@RequestMapping(value="/buscaPessoa/cnpj/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findByCnpj(@PathVariable String cnpj){
		Pessoa pessoa = pessoaService.findByCnpj(cnpj);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@RequestMapping(value="/buscaPessoa/razaoSocial/{razaoSocial}", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findByRazaoSocial(@PathVariable String razaoSocial){
		List<Pessoa> pessoas = pessoaService.findByrazaoSocial(razaoSocial);
		return ResponseEntity.ok().body(pessoas);
	}
	
	

}
