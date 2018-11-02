package JoaoVFG.com.github;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.dto.response.RotaResponseDTO;
import JoaoVFG.com.github.service.route.CalculaDistancia;
import JoaoVFG.com.github.service.route.Distancia;
import JoaoVFG.com.github.service.route.GeraRota;
import JoaoVFG.com.github.service.security.UserService;

@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest
public class TestsRota {
	@Autowired
	CalculaDistancia calculaDistancia;

	@Autowired
	GeraRota geraRota;
	
	@Autowired
	UserService userService;


	
	@Test
	public void testeGeracaoRota() {

		List<EnderecoEntregaDTO> enderecoClienteInsertDTOs = new ArrayList<EnderecoEntregaDTO>();
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281350", "435"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12285020", "1225"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12295370", "132"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281460", "100"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12288460", "100"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281420", "0"));

		RotaResponseDTO rota = geraRota.geraRota(userService.findById(1), enderecoClienteInsertDTOs);
		System.out.println(rota.toString());
		System.out.println(rota.getRota());
		assertNotNull(rota);
	}

}
