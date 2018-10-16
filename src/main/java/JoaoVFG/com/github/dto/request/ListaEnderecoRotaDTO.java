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
public class ListaEnderecoRotaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<EnderecoEntregaDTO> waypoints;

	public ListaEnderecoRotaDTO(ArrayList<EnderecoEntregaDTO> waypoints) {
		super();
		this.waypoints = waypoints;
	}
	
	
}
