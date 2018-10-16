package JoaoVFG.com.github.dto.request.insert;

import java.io.Serializable;

import JoaoVFG.com.github.entity.TipoPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsertUpdatePessoaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private Integer id;

	private TipoPessoa tipo;
	
	private String nome;
	
	private String razaoSocial;
	
	private String cpf;
	
	private String cnpj;
	
	private String dataNascimento;
	
	private String sexo;

	public InsertUpdatePessoaDTO(Integer id, TipoPessoa tipo, String nome, String razaoSocial, String cpf, String cnpj,
			String dataNascimento, String sexo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	
}
