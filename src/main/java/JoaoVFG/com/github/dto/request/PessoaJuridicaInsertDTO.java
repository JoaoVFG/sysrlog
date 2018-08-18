package JoaoVFG.com.github.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PessoaJuridicaInsertDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer tipo;
	
	private String razaoSocial;

	private String cnpj;

	public PessoaJuridicaInsertDTO(Integer tipo, String razaoSocial, String cnpj) {
		super();
		this.tipo = tipo;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	
	
}
