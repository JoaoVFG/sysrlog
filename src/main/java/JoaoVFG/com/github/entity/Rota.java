package JoaoVFG.com.github.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import JoaoVFG.com.github.entity.security.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Getter
@Setter
public class Rota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String dataCriacao;

	private String urlRota;

	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;


	public Rota(Integer id, String dataCriacao, String urlRota, User user) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.urlRota = urlRota;
		this.user = user;
	}

}
