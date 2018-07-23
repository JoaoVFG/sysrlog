package JoaoVFG.com.github.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Cep;
import JoaoVFG.com.github.domain.Cidade;
import JoaoVFG.com.github.repositories.CepRepository;
import JoaoVFG.com.github.repositories.CidadeRepository;
import JoaoVFG.com.github.service.consultaCep.CreateCep;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class CepService {

	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	CreateCep createCep;

	public Cep findById(Integer id) {
		Optional<Cep> cep = cepRepository.findById(id);

		return cep.orElseThrow(() -> new ObjectNotFoundException(
				"Cep de " + id + " não encontrado. " + "Tipo: " + Cep.class.getName()));
	}

	public List<Cep> findAll() {
		return cepRepository.findAll();
	}

	public Cep findByCep(String cepBusca) {
		String cepInfo = cepBusca;
		cepBusca = formataCep(cepBusca);
		
		Optional<Cep> cep = cepRepository.findBycep(cepBusca);
		
		if (cep.toString() == "Optional.empty") {
			Cep cepEncontrado = createCep.generateCep(cepBusca.toString());
			cep = Optional.of(cepEncontrado);
		}

		return cep.orElseThrow(() -> new ObjectNotFoundException(
				"CEP: " + cepInfo + " não foi encontrado. " + "Tipo: " + Cep.class.getName()));
	}

	public List<Cep> findByNomeRua(String nomeRua) {
		return cepRepository.findBynomeRua(nomeRua);
	}
	
	public List<Cep> findByCidade(String cidade){
		Cidade cidadeBusca = cidadeRepository.findBynome(cidade);
		return cepRepository.findByCidade(cidadeBusca);
	}

	public Cep createCep(Cep cep) {
		cep.setId(null);
		cep = cepRepository.save(cep);
		return findById(cep.getId());
	}

	public Cep updateCep(Cep updateCep) {
		Cep cep = findById(updateCep.getId());

		cep.setCep(updateCep.getCep());
		cep.setCidade(updateCep.getCidade());
		cep.setNomeRua(updateCep.getNomeRua());
		cep.setBairro(updateCep.getBairro());

		return cepRepository.save(cep);
	}

	public void deletarCep(Cep cep) {
		findById(cep.getId());

		try {
			cepRepository.deleteById(cep.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR UM CEP QUE POSSUA ENTREGAS VINCULADAS.");
			// futuro código para setar excluido = 1
		}
	}
	
	public String cepToStringEndereco(String cepBusca, String numLogradouro) {
		Cep cep = new Cep();
		StringBuilder builder = new StringBuilder();
		
		
		cep = findByCep(cepBusca);
		builder.append(cep.getNomeRua());
		builder.append(" ");
		builder.append(numLogradouro);
		builder.append(", ");
		builder.append(cep.getBairro());
		builder.append(", ");
		builder.append(cep.getCidade().getNome());
		builder.append(", ");
		builder.append(cep.getCidade().getEstado().getNome());
		builder.append(", ");
		builder.append(cep.getCep());
		
		return builder.toString();
		
	}
	
	public String formataCep(String cep) {
		return cep.replace("-", "");
	}
}
