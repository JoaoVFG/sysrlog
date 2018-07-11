package JoaoVFG.com.github.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Cep;
import JoaoVFG.com.github.domain.Cidade;
import JoaoVFG.com.github.domain.Estado;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.CidadeService;
import JoaoVFG.com.github.service.EstadoService;

/**
 * Entidade baseada nos dados do WS do viacep.com Criada a baseado no código de
 * Ulisses Ricardo de Souza Silva https://github.com/uliss3s/ceputil
 */

@Service
public class CreateCep {

	@Autowired(required = true)
	CepService cepService;

	@Autowired(required = true)
	CidadeService cidadeService;

	@Autowired(required = true)
	EstadoService estadoService;

	public Cep generateCep(String cepConsulta) {
		ConsultaViaCep consultaViaCep = new ConsultaViaCep();

		EnderecoConsulta enderecoConsulta = consultaViaCep.consultaCep(cepConsulta);
		
		if(enderecoConsulta == null){
			return null;
		}
		
		Estado estado = FindOrCreateEstado(enderecoConsulta);
		Cidade cidade = findOrCreateCidade(enderecoConsulta);
		
		Cep cep = saveCep(enderecoConsulta, estado, cidade);
		
		return cep;
		
		
	}

	public Estado FindOrCreateEstado(EnderecoConsulta enderecoConsulta) {

		Estado estado = new Estado();

		estado = estadoService.findBySiglaAux(enderecoConsulta.getUf());

		if (estado == null) {
			return estadoService.createEstado(new Estado(null, "", enderecoConsulta.getUf()));
		}

		return estado;

	}

	public Cidade findOrCreateCidade(EnderecoConsulta enderecoConsulta) {

		Cidade cidade = new Cidade();

		cidade = cidadeService.findByNomeCidadeSiglaEstadoAux(enderecoConsulta.getUf(),
				enderecoConsulta.getLocalidade());

		if (cidade == null) {
			return cidadeService.createCidade(new Cidade(null, enderecoConsulta.getLocalidade(),
					estadoService.findBySiglaAux(enderecoConsulta.getUf())));
		}

		return cidade;
	}

	public Cep saveCep(EnderecoConsulta EnderecoConsulta, Estado estado, Cidade cidade) {

		Cep cep = new Cep(null, 
				Integer.parseInt(EnderecoConsulta.getCep().replaceAll("-", "")),
				EnderecoConsulta.getLogradouro(), 
				EnderecoConsulta.getBairro(), 
				cidade);

		cep = cepService.createCep(cep);
		
		return cep;
	}

}
