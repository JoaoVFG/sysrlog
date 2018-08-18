package JoaoVFG.com.github.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioInsertDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idPessoa;
	
	private Integer idEmpresa;
	
	private Integer idCargo;

	public FuncionarioInsertDTO(Integer idPessoa, Integer idEmpresa, Integer idCargo) {
		super();
		this.idPessoa = idPessoa;
		this.idEmpresa = idEmpresa;
		this.idCargo = idCargo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FuncionarioInsertDTO [idPessoa=");
		builder.append(idPessoa);
		builder.append(", idEmpresa=");
		builder.append(idEmpresa);
		builder.append(", idCargo=");
		builder.append(idCargo);
		builder.append("]");
		return builder.toString();
	}
	
	

}
