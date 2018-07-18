package JoaoVFG.com.github.service.route;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
/**
 * *
 * @author joao.garcia
 * A parte do Parse de XML só pode ser realizada pelo posto do usuário Jai Dixit 
 * No link: https://stackoverflow.com/questions/40561264/how-to-use-google-maps-distance-matrix-java-api-to-obtain-closest-distance-betwe/48487598#48487598?newreg=a944c57ea41c4e3092b502efb401471b
 */

@Service
public class CalculaDistancia {
	
	public Distancia calcDistancia(String cepOrigem, String cepDestino) {
		
		JsonObject objectResponse = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://maps.googleapis.com/maps/api/distancematrix/json?origins=" + cepOrigem + 
					"&destinations=" + cepDestino + "&travelmode=driving");
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			
			objectResponse = Json.createReader(entity.getContent()).readObject();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return generateDistancia(objectResponse, cepOrigem, cepDestino);
	}
	
	
	public Distancia generateDistancia(JsonObject object, String cepOrigem, String cepDestino) {
		
		Distancia distancia = new Distancia();
		distancia.setCepOrigem(cepOrigem);
		distancia.setCepDestino(cepDestino);
		
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
			return null;
		}
		
	}
}
