package JoaoVFG.com.github.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ResponsavelEntregaCepRota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "CEP_ID")
	private Cep cep;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "ROTA_ID")
	@JsonIgnore
	private Rota rota;

	public ResponsavelEntregaCepRota(Integer id, Cep cep, Empresa empresa, Rota rota) {
		super();
		this.id = id;
		this.cep = cep;
		this.empresa = empresa;
		this.rota = rota;
	}
	
	
}
