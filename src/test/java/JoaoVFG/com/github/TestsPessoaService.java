package JoaoVFG.com.github;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.TipoPessoa;
import JoaoVFG.com.github.service.PessoaService;
import JoaoVFG.com.github.service.TipoPessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsPessoaService {
	
	@Autowired
	TipoPessoaService tipoPessoaService;
	
	@Autowired
	PessoaService pessoaService;
	
	
	@Test
	public void testeTipoPessoaBuscaTodos() {
		List<TipoPessoa> tiposPessoa= tipoPessoaService.findAll();
		assertNotNull(tiposPessoa);
	}
	
	@Test
	public void testeTipoPessoaBuscaNome() {
		TipoPessoa tipoPessoa = tipoPessoaService.findBydescricao("FISICA");
		assertEquals("1", tipoPessoa.getId().toString());
	}
	
	@Test
	public void testeTipoPessoaBusca() {
		TipoPessoa tipoPessoa = tipoPessoaService.findById(2);
		assertEquals("JURIDICA", tipoPessoa.getDescricao());
	}
	
	@Test
	public void testeInserePessoaJuridica() {
		Pessoa pessoa = new Pessoa(null, tipoPessoaService.findBydescricao("JURIDICA"), "ENVIAMOSJA","78474501000398");
		pessoaService.create(pessoa);
	}
	
	@Test
	public void testeInserePessoaFisica() {
		Pessoa pessoa = new Pessoa(null, tipoPessoaService.findBydescricao("FISICA"), "TESTE", "45665445689", "20/08/1977", "F");
		pessoaService.create(pessoa);
	}
	
	@Test
	public void testeBuscaPessoaPorId(){
		Pessoa pessoa = pessoaService.findById(1);
		assertEquals("JV", pessoa.getNome());
	}
	
	@Test
	public void testeBuscaTodasPessoas() {
		List<Pessoa> pessoas = pessoaService.findAll();
		assertNotNull(pessoas);
	}
	
	@Test
	public void testeBuscaPessoaPorCpf() {
		Pessoa pessoa = pessoaService.findByCpf("11593054807");
		assertEquals("JJ", pessoa.getNome());
	}
	
	@Test
	public void testeBuscaPessoaPorCnpj() {
		Pessoa pessoa = pessoaService.findByCnpj("4485478220008");
		assertEquals("trans B", pessoa.getRazaoSocial());
	}
	
	@Test
	public void testeBuscaPorRazaoSocial() {
		List<Pessoa> pessoas = pessoaService.findByrazaoSocial("trans");
		assertNotNull(pessoas);
	}
}
