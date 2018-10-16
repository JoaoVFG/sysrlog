package JoaoVFG.com.github.dto.request.insert;

import java.io.Serializable;
import java.util.Set;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InsertUpdateUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String senha;

	private String apiKey;

	private Pessoa pessoa;

	private Set<Role> roles;
	
	public InsertUpdateUserDTO(Integer id, String email, String senha, String apiKey) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.apiKey = apiKey;
	}
	
	

}
