package JoaoVFG.com.github.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegiaoInsertByCepsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Integer empresaId;
	private List<String> ceps;
	
	
	public RegiaoInsertByCepsDTO(String descricao, Integer empresaId, List<String> ceps) {
		super();
		this.descricao = descricao;
		this.empresaId = empresaId;
		this.ceps = ceps;
	}
	
	
}
