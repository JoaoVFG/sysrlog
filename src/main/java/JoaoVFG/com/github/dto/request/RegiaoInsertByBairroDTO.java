package JoaoVFG.com.github.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegiaoInsertByBairroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descricao;
	private Integer empresaId;
	private String cidade;
	private String bairro;

	public RegiaoInsertByBairroDTO(String descricao, Integer empresaId, String cidade, String bairro) {
		super();
		this.descricao = descricao;
		this.empresaId = empresaId;
		this.cidade = cidade;
		this.bairro = bairro;
	}

}
