package JoaoVFG.com.github;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.service.route.CalculaDistancia;
import JoaoVFG.com.github.service.route.Distancia;
import JoaoVFG.com.github.service.route.GeraRota;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsRota {
	@Autowired
	CalculaDistancia calculaDistancia;

	@Autowired
	GeraRota geraRota;

	@Test
	public void testCalcDistance() {
		Distancia dis = calculaDistancia.calcDistancia("12288560", "12285020");
		System.out.println(dis.getCepOrigem());
		System.out.println(dis.getCepDestino());
		System.out.println(dis.getTimeInSeconds());
		System.out.println(dis.getDistanciaInMeters());
		System.out.println(dis.toString());
	}
	/**
	@Test
	public void testAchouMenor() {
		String filial = "12288560";

		List<EnderecoEntregaDTO> enderecoClienteInsertDTOs = new ArrayList<EnderecoEntregaDTO>();
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281350", "435"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12285020", "1225"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12295370", "132"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281460", "100"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12288460", "100"));
		enderecoClienteInsertDTOs.add(new EnderecoEntregaDTO("12281420", "s/n"));

		geraRota.geraRota(filial, 0, enderecoClienteInsertDTOs);
	}**/

}
