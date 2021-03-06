package JoaoVFG.com.github.entity.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import JoaoVFG.com.github.entity.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	private String senha;
	
	private String apiKey;

	@ManyToOne
	@JoinColumn(name = "PESSOA_ID")
	private Pessoa pessoa;

	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;

	public User(Integer id, String email, String senha, Pessoa pessoa, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.pessoa = pessoa;
		this.roles = roles;
	}

	

}
