package JoaoVFG.com.github.dto.response;

import JoaoVFG.com.github.entity.security.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginResponse {
	
	String token;
	User user;
	public LoginResponse(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	

}
