package JoaoVFG.com.github.dto.request.insert;

import java.io.Serializable;
import java.util.Set;

import JoaoVFG.com.github.entity.Cep;
import JoaoVFG.com.github.entity.Empresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsertUpdateRegiaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private String descricao;
	
	private Empresa empresa;

	private Set<Cep> ceps;

	public InsertUpdateRegiaoDTO(Integer id, String descricao, Empresa empresa, Set<Cep> ceps) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.empresa = empresa;
		this.ceps = ceps;
	}
	
	
	
}
