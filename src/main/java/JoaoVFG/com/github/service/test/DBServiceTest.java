package JoaoVFG.com.github.service.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Pessoa;
import JoaoVFG.com.github.repositories.PessoaRepository;
import JoaoVFG.com.github.repositories.TipoPessoaRepository;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.consultaCep.CreateCep;

@Service
public class DBServiceTest {
	
	@Autowired
	CepService cepService;

	@Autowired
	CreateCep createCep;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	TipoPessoaRepository tipo;
	
	public void instantiateTesteDataBase() {
		
		createCep.generateCep("12288560");
		createCep.generateCep("12281350");
		createCep.generateCep("12285020");
		createCep.generateCep("12295370");
		createCep.generateCep("12281460");
		createCep.generateCep("12288460");
		createCep.generateCep("12281420");
		
		Pessoa pessoaf1 = new Pessoa(null, tipo.findByid(1), "JV", "45567860889", "21/07/1996", "M");
		Pessoa pessoaf2 = new Pessoa(null, tipo.findByid(1), "JJ", "15593054807", "01/11/1967", "M");
		Pessoa pessoaf3 = new Pessoa(null, tipo.findByid(1), "Ingrid", "89988998899", "11/08/1993", "F");
		Pessoa pessoaf4 = new Pessoa(null, tipo.findByid(1), "carla", "58963221474", "13/04/1970", "F");
		Pessoa pessoaj1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans A", "4478969850008");
		Pessoa pessoaj2 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans B", "4485478220008");
		Pessoa pessoaj3 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A", "485874810008");
		Pessoa pessoaj4 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "mandou X", "898725950008");
		pessoaRepository.saveAll(Arrays.asList(pessoaf1,pessoaf2,pessoaf3,pessoaf4,pessoaj1,pessoaj2,pessoaj3,pessoaj4));
		
		
		
	}

}
