package JoaoVFG.com.github.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private TipoPessoa tipo;
	
	private String nome;
	
	private String razaoSocial;
	
	private String cpf;
	
	private String cpnj;
	
	private String dataNascimento;
	
	private String sexo;
	
	//Construtor Pessoa Fisica
	public Pessoa(TipoPessoa tipo, String nome, String cpf, String dataNascimento, String sexo) {
		this.tipo = tipo;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	//Construtor Pessoa juridica
	public Pessoa(TipoPessoa tipo, String razaoSocial, String cnpj) {
		this.tipo = tipo;
		this.razaoSocial = razaoSocial;
		this.cpnj = cnpj;
	}
	
	
}
