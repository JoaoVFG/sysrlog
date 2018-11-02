package JoaoVFG.com.github.service.route;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.service.MapConfigService;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;
/**
 * *
 * @author joao.garcia
 * A parte do Parse de XML só pode ser realizada pelo post do usuário Jai Dixit 
 * No link: https://stackoverflow.com/questions/40561264/how-to-use-google-maps-distance-matrix-java-api-to-obtain-closest-distance-betwe/48487598#48487598?newreg=a944c57ea41c4e3092b502efb401471b
 */

@Service
public class CalculaDistancia {
	
	@Autowired
	MapConfigService mapConfigService;
	

	public EnderecoEntregaDTO findMenorDistancia(String cepOrigem, List<EnderecoEntregaDTO> enderecosCliente) {
		
		
		List<Distancia> distanciasDoInicio = new ArrayList<Distancia>();
		
		for (EnderecoEntregaDTO e: enderecosCliente) {
			Distancia d = calcDistancia(cepOrigem, e);
			distanciasDoInicio.add(d);
		}
		distanciasDoInicio.sort(Comparator.comparing(Distancia::getDistanciaInMeters));
		
		return distanciasDoInicio.get(0).getEnderecoEntregaDTO();
		
		
	}
	
	public Distancia calcDistancia(String cepOrigem, EnderecoEntregaDTO enderecoEntregaDTO) {
		
		JsonObject objectResponse = null;
		
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String url = "https://maps.googleapis.com/maps/api/distancematrix/json?"
					+ "origins=" + URLEncoder.encode(cepOrigem,"UTF-8") 
					+ "&destinations=" + URLEncoder.encode(enderecoEntregaDTO.getEnderecoString(),"UTF-8") + "&travelmode=driving"
					+ "&key=" + mapConfigService.findGoogleApiKey().getValue();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			objectResponse = Json.createReader(entity.getContent()).readObject();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return generateDistancia(objectResponse, cepOrigem, enderecoEntregaDTO);
	}
	
	
	public Distancia generateDistancia(JsonObject object, String cepOrigem,EnderecoEntregaDTO enderecoEntregaDTO) {
		
		Distancia distancia = new Distancia();
		distancia.setCepOrigem(cepOrigem);
		distancia.setEnderecoEntregaDTO(enderecoEntregaDTO);
		
		String status = object.getString("status");
		
		if(status.equals("OK") ) {

			JsonArray dist = (JsonArray) object.get("rows");
			JsonObject objAux = (JsonObject) dist.get(0);
			JsonArray disting = (JsonArray) objAux.get("elements");
			JsonObject objAuxFinal = (JsonObject) disting.get(0);
			
			JsonObject distanciaJson = (JsonObject) objAuxFinal.get("distance");
			JsonObject tempoJson = (JsonObject) objAuxFinal.get("duration");
			distancia.setDistanciaInMeters(Integer.parseInt(distanciaJson.get("value").toString()));
			distancia.setTimeInSeconds(Integer.parseInt(tempoJson.get("value").toString()));			
			
			return distancia;
			
		}else {
			throw new ObjectNotFoundException("Não foi possivel realizar o calculo da distancia entre os pontos");
		}
		
	}
}
