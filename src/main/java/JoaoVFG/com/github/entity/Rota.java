package JoaoVFG.com.github.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import JoaoVFG.com.github.entity.security.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CEPS_ROTA", joinColumns = { @JoinColumn(name = "ROTA_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CEP_ID") })
	private Set<Cep> ceps;

	public Rota(Integer id, String dataCriacao, String urlRota, User user) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.urlRota = urlRota;
		this.user = user;
	}

}
