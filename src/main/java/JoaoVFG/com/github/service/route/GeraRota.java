package JoaoVFG.com.github.service.route;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.dto.EnderecoClienteDTO;
import JoaoVFG.com.github.service.CepService;

@Service
public class GeraRota {
	
	@Autowired
	CalculaDistancia calculaDistancia;
	
	@Autowired
	CepService cepService;
	
	
	public void geraRota(String filial, int idRegiao, List<EnderecoClienteDTO> enderecoClienteDTOs) {
		List<String> listaEnderecosString = enderecoClienteDtoToString(enderecoClienteDTOs);
		String enderecoString = filial;
		
		List<String> listaRota = new ArrayList<String>();


		while(!listaEnderecosString.isEmpty()) {
			System.out.println(enderecoString);
			enderecoString = findMenorDistancia(enderecoString, listaEnderecosString);
			listaRota.add(enderecoString);
			listaEnderecosString.remove(enderecoString);
		}
		
		System.out.println(listaRota);
		geraUrlMaps(listaRota, filial);
	}
	
	
	public String geraUrlMaps(List<String> listaRota, String filial) {
		
		StringBuilder builder = new StringBuilder();
		
		String sufixo = "https://www.google.com/maps/dir/?api=1";
		builder.append(sufixo);

		try {

			builder.append("&origin=");
			builder.append(URLEncoder.encode(filial,"UTF-8"));
			builder.append("&destination=");
			builder.append(URLEncoder.encode(filial,"UTF-8"));
			builder.append("&waypoints=");
			for (String e: listaRota) {
				builder.append(URLEncoder.encode(e,"UTF-8"));
				builder.append("|");
			}
			
			builder.append("&travelmode=driving");
		} catch (UnsupportedEncodingException e1) {
			return null;
		}
		
		System.out.println(builder.toString());
		return (builder.toString());
		
	}
	
	public String findMenorDistancia(String cepOrigem, List<String> enderecosCliente) {
		
		
		List<Distancia> distanciasDoInicio = new ArrayList<Distancia>();
		
		for (String e: enderecosCliente) {
			Distancia d = calculaDistancia.calcDistancia(cepOrigem, e);
			distanciasDoInicio.add(d);
		}
		
		distanciasDoInicio.sort(Comparator.comparing(Distancia::getDistanciaInMeters));
		
		return distanciasDoInicio.get(0).getCepDestino();
		
		
	}
	
	public List<String> enderecoClienteDtoToString(List<EnderecoClienteDTO> enderecos){
		List<String> enderecosString = new ArrayList<String>();
		
		for (EnderecoClienteDTO e: enderecos) {
			enderecosString.add(cepService.cepToStringEndereco(e.getCep(),e.getNumeroLogradouro()));
		}
		
		return enderecosString;
	}
	
	
	
	
}
