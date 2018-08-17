package JoaoVFG.com.github.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegiaoInsertByCidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descricao;
	private Integer empresaId;
	private String estado;
	private String cidade;

	public RegiaoInsertByCidadeDTO(String descricao, Integer empresaId, String estado, String cidade) {
		super();
		this.descricao = descricao;
		this.empresaId = empresaId;
		this.estado = estado;
		this.cidade = cidade;
	}

}
