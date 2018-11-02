package JoaoVFG.com.github.service.route;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Distancia {
	private String cepOrigem;
	private EnderecoEntregaDTO enderecoEntregaDTO;
	private int distanciaInMeters;
	private int timeInSeconds;
	
	
	public Distancia(String cepOrigem, EnderecoEntregaDTO enderecoEntregaDTO, int distancia, int timeInSeconds) {
		super();
		this.cepOrigem = cepOrigem;
		this.enderecoEntregaDTO = enderecoEntregaDTO;
		this.distanciaInMeters = distancia;
		this.timeInSeconds = timeInSeconds;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Distancia [cepOrigem=");
		builder.append(cepOrigem);
		builder.append(", cepDestino=");
		builder.append(enderecoEntregaDTO.getEnderecoString());
		builder.append(", distanciaInMeters=");
		builder.append(distanciaInMeters);
		builder.append(", timeInSeconds=");
		builder.append(timeInSeconds);
		builder.append("]");
		return builder.toString();
	}
	
	

}
