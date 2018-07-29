package JoaoVFG.com.github;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.entity.TipoPessoa;
import JoaoVFG.com.github.repositories.TipoPessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsPessoaService {
	
	@Autowired
	TipoPessoaRepository tipoPessoaRepository;
	
	@Test
	public void testeTipoPessoaBuscaTodos() {
		List<TipoPessoa> tiposPessoa= tipoPessoaRepository.findAll();
		assertNotNull(tiposPessoa);
	}
	
	@Test
	public void testeTipoPessoaBuscaNome() {
		TipoPessoa tipoPessoa = tipoPessoaRepository.findBydescricao("FISICA");
		assertEquals("1", tipoPessoa.getId().toString());
	}
	
	@Test
	public void testeTipoPessoaBusca() {
		TipoPessoa tipoPessoa = tipoPessoaRepository.buscaPorId(2);
		assertEquals("JURIDICA", tipoPessoa.getDescricao());
	}
	
}
