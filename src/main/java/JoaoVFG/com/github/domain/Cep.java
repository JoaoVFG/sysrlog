package JoaoVFG.com.github.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
@Entity
public class Cep {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cep;
	
	private String nome_rua;
	
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Cep(Integer cep, String nome_rua, String bairro, Cidade cidade) {
		super();
		this.cep = cep;
		this.nome_rua = nome_rua;
		this.bairro = bairro;
		this.cidade = cidade;
	}
}
