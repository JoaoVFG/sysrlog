package JoaoVFG.com.github.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PessoaFisicaInsertDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private Integer tipo;
	
	private String cpf;
	
	private String dataNascimento;
	
	private String sexo;

	public PessoaFisicaInsertDTO(String nome, Integer tipo, String cpf, String dataNascimento, String sexo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	
}
