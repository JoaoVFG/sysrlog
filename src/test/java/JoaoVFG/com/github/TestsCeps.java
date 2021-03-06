package JoaoVFG.com.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.consultaCep.CreateCep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsCeps {

	@Autowired
	CepService cepService;

	@Autowired
	CreateCep createCep;

	@Test
	public void contextLoads() {
	}

	
	@Test
	public void testeFindOrCreateCep() {
		System.out.println("geracep");
		//createCep.generateCep("12288560");
	}

	@Test
	public void testFindCep() {
		System.out.println("busca por cep");
		cepService.findByCep("12020220");
		cepService.findByCep("12061001");
		cepService.findByCep("12061600");
		cepService.findByCep("12062490");
		cepService.findByCep("12071110");
		cepService.findByCep("12423060");
		cepService.findByCep("12050410");
	}


}
