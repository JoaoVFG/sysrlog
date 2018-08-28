package JoaoVFG.com.github.dto.response;

import JoaoVFG.com.github.entity.Cep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CepResponseDTO {
	
	String cep;
	String nomeRua;
	String nomeBairro;
	String nomeCidade;
	String nomeEstado;
	
	public CepResponseDTO(Cep cep) {
		this.cep = cep.getCep();
		this.nomeRua = cep.getNomeRua();
		this.nomeBairro = cep.getBairro();
		this.nomeCidade = cep.getCidade().getNome();
		this.nomeEstado = cep.getCidade().getEstado().getNome();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CepResponseDTO [cep=");
		builder.append(cep);
		builder.append(", nomeRua=");
		builder.append(nomeRua);
		builder.append(", nomeBairro=");
		builder.append(nomeBairro);
		builder.append(", nomeCidade=");
		builder.append(nomeCidade);
		builder.append(", nomeEstado=");
		builder.append(nomeEstado);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
