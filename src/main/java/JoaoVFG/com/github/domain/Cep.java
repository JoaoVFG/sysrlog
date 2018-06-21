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
	
	private String nomeRua;
	
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Cep(Integer cep, String nomeRua, String bairro, Cidade cidade) {
		super();
		this.cep = cep;
		this.nomeRua = nomeRua;
		this.bairro = bairro;
		this.cidade = cidade;
	}
}
