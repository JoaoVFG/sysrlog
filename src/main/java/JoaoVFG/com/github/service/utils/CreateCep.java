package JoaoVFG.com.github.service.utils;

import org.springframework.beans.factory.annotation.Autowired;

import JoaoVFG.com.github.domain.Cep;
import JoaoVFG.com.github.domain.Estado;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.CidadeService;
import JoaoVFG.com.github.service.EstadoService;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

/**
 * Entidade baseada nos dados do WS do viacep.com
 * Criada a baseado no código de Ulisses Ricardo de Souza Silva
 * https://github.com/uliss3s/ceputil
 */

public class CreateCep {

	@Autowired
	CepService cepService;
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	EstadoService estadoService;
	
	
	public void generateCep(String cep) {
		ConsultaViaCep consultaViaCep = new ConsultaViaCep();
		
		EnderecoConsulta enderecoConsulta = consultaViaCep.consultaCep(cep);
		
		//Estado estado = FindOrCreateEstado(enderecoConsulta);
		
		//System.out.println(estado.getId());
		//System.out.println(estado.getSigla());
		
	}
	
	/**
	public Estado FindOrCreateEstado(EnderecoConsulta enderecoConsulta) {
		
		if(estadoService.findBySigla(enderecoConsulta.getUf()).equals(null)) {
			Estado estado = new Estado(null, enderecoConsulta.getUf());
			estadoService.createEstado(estado);
		}
		
		return estadoService.findBySigla(enderecoConsulta.getUf());
	}
	*/
	
}
