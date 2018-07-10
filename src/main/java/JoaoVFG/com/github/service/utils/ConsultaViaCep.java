package JoaoVFG.com.github.service.utils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Entidade baseada nos dados do WS do viacep.com
 * Criada a baseado no código de Ulisses Ricardo de Souza Silva
 * https://github.com/uliss3s/ceputil
 */
public class ConsultaViaCep {

	public EnderecoConsulta consultaCep(String cep) {
		
		JsonObject objectResponse = null;
		
		try {
			
			if(!ValidaCep.validaCep(cep)) {
				throw new RuntimeException("Formato de Cep inválido");
			}
			
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet	httpGet = new HttpGet("https://viacep.com.br/ws/"+cep+"/json");
			HttpResponse response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			
			objectResponse = Json.createReader(entity.getContent()).readObject();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return generateEnderecoConsulta(objectResponse);
	}
	
	
	private EnderecoConsulta generateEnderecoConsulta(JsonObject object) {
		
		EnderecoConsulta enderecoConsulta = new EnderecoConsulta();
		
		JsonValue erro = object.get("erro");
		
		if(erro == null) {
			enderecoConsulta.setCep(object.getString("cep"));
			enderecoConsulta.setLogradouro(object.getString("logradouro"));
			enderecoConsulta.setComplemento(object.getString("complemento"));
			enderecoConsulta.setBairro(object.getString("bairro"));
			enderecoConsulta.setLocalidade(object.getString("localidade"));
			enderecoConsulta.setUf(object.getString("uf"));
			enderecoConsulta.setUnidade(object.getString("unidade"));
			enderecoConsulta.setIbge(object.getString("ibge"));
			enderecoConsulta.setGia(object.getString("gia"));
			
		}
		
		return enderecoConsulta;
		
		
	}
	
}
