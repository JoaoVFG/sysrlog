package JoaoVFG.com.github.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelefoneInsertDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String tipoNumero;
	
	private String numero;
	
	private Integer idPessoa;

	public TelefoneInsertDTO(String tipoNumero, String numero, Integer idPessoa) {
		super();
		this.tipoNumero = tipoNumero;
		this.numero = numero;
		this.idPessoa = idPessoa;
	}
	
	
}
