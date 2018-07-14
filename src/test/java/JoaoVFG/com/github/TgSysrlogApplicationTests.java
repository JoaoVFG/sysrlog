package JoaoVFG.com.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.CidadeService;
import JoaoVFG.com.github.service.EstadoService;
import JoaoVFG.com.github.service.utils.CreateCep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TgSysrlogApplicationTests {

	@Autowired
	EstadoService estadoService;

	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	CepService cepService;

	@Autowired
	CreateCep createCep;

	@Test
	public void contextLoads() {
	}

	/**
	 * @Rollback
	 * @Test public void testeInsereEstado() { Estado estado = new Estado(null,
	 *       "Teste4", "Teste4"); estadoService.createEstado(estado);
	 *       assertEquals(estadoService.findByNome("Teste4").getNome(), "Teste4"); }
	 * 
	 * @Rollback
	 * @Test public void testeInsereCidade() { Estado estado = new Estado(null,
	 *       "Teste2", "Teste2"); estadoService.createEstado(estado); Cidade cidade
	 *       = new Cidade(null, "teste",estadoService.findBySigla("Teste2"));
	 *       cidadeService.createCidade(cidade); }
	 */

	/** @Commit
	@Test
	public void testeFindOrCreateEstado() {
		createCep.generateCep("12288560");
	}
	**/
	
	@Test
	public void testFindCep() {
		cepService.findByCep("12295370");
	}
	
}
