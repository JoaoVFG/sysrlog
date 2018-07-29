package JoaoVFG.com.github;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.domain.Pessoa;
import JoaoVFG.com.github.repositories.PessoaRepository;
import JoaoVFG.com.github.repositories.TipoPessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsPessoa {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	TipoPessoaRepository tipo;
	
	@Test
	public void createPessoa() {
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
	
	@Test
	public void findByTipo() {
		Optional<List<Pessoa>> pessoas = pessoaRepository.findBytipo(tipo.findByid(1));
		for (Optional<List<Pessoa>> p : Arrays.asList(pessoas)) {
			System.out.println(p.toString());
		}
	}
	/**
	@Test
	public void findByCpf() {
		Optional<Pessoa> pessoa = pessoaRepository.findBycpf("45567860889");
		System.out.println(pessoa.get().getNome());
		assertEquals("JV", pessoa.get().getNome());
		
	}
	**/
	
	/**
	@Test
	public void findByCnpj() {
		Optional<Pessoa> pessoa = pessoaRepository.findBycnpj("898725950008");
		assertEquals("mandou X", pessoa.get().getRazaoSocial());
	}
	**/
	
	/**
	@Test
	public void testFindRazaoSocialContains() {
		Optional<List<Pessoa>> pessoas = pessoaRepository.findByrazaoSocialContains("trans");
		assertNotNull(pessoas);
	}
	**/
}
