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
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Pessoa pessoa;
	
	private Cep cep;
	
	private Integer numeroLogradouro;
	
	private String complemento;
	

	public Endereco(Integer id, Pessoa pessoa, Cep cep, Integer numeroLogradouro, String complemento) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.cep = cep;
		this.numeroLogradouro = numeroLogradouro;
		this.complemento = complemento;
	}
	
	
	
}
