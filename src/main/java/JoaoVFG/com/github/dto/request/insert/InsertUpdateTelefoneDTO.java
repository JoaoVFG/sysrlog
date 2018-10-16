package JoaoVFG.com.github.dto.request.insert;

import java.io.Serializable;

import JoaoVFG.com.github.entity.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsertUpdateTelefoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String tipoNumero;

	private String numero;

	private Pessoa pessoa;

	public InsertUpdateTelefoneDTO(Integer id, String tipoNumero, String numero, Pessoa pessoa) {
		super();
		this.id = id;
		this.tipoNumero = tipoNumero;
		this.numero = numero;
		this.pessoa = pessoa;
	}

}
