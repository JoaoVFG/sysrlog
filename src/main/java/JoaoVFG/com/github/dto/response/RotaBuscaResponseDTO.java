package JoaoVFG.com.github.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import JoaoVFG.com.github.entity.Rota;
import JoaoVFG.com.github.entity.RotaEndereco;
import JoaoVFG.com.github.entity.Empresa;
import JoaoVFG.com.github.entity.ResponsavelEntregaCepRota;
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
public class RotaBuscaResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idRota;
	private String urlRota;
	private String dataCriacao;

	private String usuarioCriador;

	private String Empresa;

	private List<String> listaEnderecoEntrega;

	private List<String> responsavelEntregaCepRotas;

	public RotaBuscaResponseDTO(Rota rota, Empresa empresa, List<RotaEndereco> listaEnderecoEntrega,
			List<ResponsavelEntregaCepRota> responsavelEntregaCepRotas) {
		this.idRota = rota.getId();
		this.urlRota = rota.getUrlRota();
		this.dataCriacao = rota.getDataCriacao();
		this.usuarioCriador = rota.getUser().getEmail();

		this.listaEnderecoEntrega = geraListaEnderecoEntrega(listaEnderecoEntrega);
		
		this.responsavelEntregaCepRotas = geraListaResponsavelEntrega(responsavelEntregaCepRotas);

	}

	private List<String> geraListaEnderecoEntrega(List<RotaEndereco> enderecos) {
		List<String> listaEnderecoEntrega = new ArrayList<>();
		for (RotaEndereco e : enderecos) {
			listaEnderecoEntrega.add("CEP: " + e.getEndereco().getCep().getCep() + ". Nome Rua: "
					+ e.getEndereco().getCep().getNomeRua() + ". Bairro: " + e.getEndereco().getCep().getBairro()
					+ ". Cidade:" + e.getEndereco().getCep().getCidade().getNome() + ". Estado: "
					+ e.getEndereco().getCep().getCidade().getEstado().getSigla() + ". Numero Logradouro: "
					+ e.getEndereco().getNumeroLogradouro() + ". Complemento: " + e.getEndereco().getComplemento());
		}
		return listaEnderecoEntrega;
	}

	private List<String> geraListaResponsavelEntrega(List<ResponsavelEntregaCepRota> responsavelEntregaCepRotas) {
		List<String> listaResponsavelEntrega = new ArrayList<>();
		for (ResponsavelEntregaCepRota r : responsavelEntregaCepRotas) {
			listaResponsavelEntrega
					.add("CEP: " + r.getCep().getCep() + ". Empresa" + r.getEmpresa().getPessoa().getRazaoSocial());
		}
		return listaResponsavelEntrega;
	}

}
