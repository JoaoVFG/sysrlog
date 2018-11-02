package JoaoVFG.com.github.security;

import java.util.List;

import JoaoVFG.com.github.dto.request.EnderecoEntregaDTO;
import JoaoVFG.com.github.dto.response.ResponsavelRegiaoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListasEnderecos {
	
	List<ResponsavelRegiaoDTO> responsavelRegiaoDTO;
	List<EnderecoEntregaDTO> enderecoEntregaDTOs;
	public ListasEnderecos(List<ResponsavelRegiaoDTO> responsavelRegiaoDTO,
			List<EnderecoEntregaDTO> enderecoEntregaDTOs) {
		super();
		this.responsavelRegiaoDTO = responsavelRegiaoDTO;
		this.enderecoEntregaDTOs = enderecoEntregaDTOs;
	}

	
}
