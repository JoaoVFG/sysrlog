package JoaoVFG.com.github.dto.request;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListaEnderecoRotaDTOwithUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUser;
	private ArrayList<EnderecoEntregaDTO> waypoints;
	
	public ListaEnderecoRotaDTOwithUser(Integer idUser, ArrayList<EnderecoEntregaDTO> waypoints) {
		super();
		this.idUser = idUser;
		this.waypoints = waypoints;
	}
	
	
	
	
}
