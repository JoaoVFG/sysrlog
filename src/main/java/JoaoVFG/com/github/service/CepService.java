package JoaoVFG.com.github.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Cep;
import JoaoVFG.com.github.repositories.CepRepository;
import JoaoVFG.com.github.service.utils.CreateCep;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class CepService {

	@Autowired
	CepRepository cepRepository;

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
		Optional<Cep> cep = cepRepository.findBycep(cepBusca);
		System.out.println(cep.toString());
		
		if (cep.toString() == "Optional.empty") {
			Cep cepEncontrado = createCep.generateCep(cepBusca.toString());
			cep = Optional.of(cepEncontrado);
		}else {
			System.out.println("Não funfou");
		}

		return cep.orElseThrow(() -> new ObjectNotFoundException(
				"CEP: " + cepBusca + " não foi encontrado. " + "Tipo: " + Cep.class.getName()));
	}

	public LinkedList<Cep> findByNomeRua(String nomeRua) {
		return cepRepository.findBynomeRua(nomeRua);
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
}
