package JoaoVFG.com.github;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		Pessoa pessoaf = new Pessoa(null, tipo.findByid(1), "PAS", "11122233344", "18/06/1989", "M");
		pessoaRepository.save(pessoaf);
	}
	
	@Test
	public void findByTipo() {
		Optional<List<Pessoa>> pessoas = pessoaRepository.findBytipo(tipo.findByid(1));
		for (Optional<List<Pessoa>> p : Arrays.asList(pessoas)) {
			System.out.println(p.toString());
		}
	}
	
	@Test
	public void findByCpf() {
		Optional<Pessoa> pessoa = pessoaRepository.findBycpf("45567860889");
		System.out.println(pessoa.get().getNome());
		assertEquals("JV", pessoa.get().getNome());
		
	}
	
	
	
	@Test
	public void findByCnpj() {
		Optional<Pessoa> pessoa = pessoaRepository.findBycnpj("898725950008");
		assertEquals("mandou X", pessoa.get().getRazaoSocial());
	}
	
	
	
	@Test
	public void testFindRazaoSocialContains() {
		Optional<List<Pessoa>> pessoas = pessoaRepository.findByrazaoSocialContains("trans");
		assertNotNull(pessoas);
	}
	
}
