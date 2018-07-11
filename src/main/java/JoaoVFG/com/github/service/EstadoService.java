package JoaoVFG.com.github.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Estado;
import JoaoVFG.com.github.repositories.EstadoRepository;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	
	public Estado findById(Integer id) {
		Optional<Estado> estado = estadoRepository.findById(id);

		return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
																	". Tipo: " + Estado.class.getName()));
	}

	
	public List<Estado> findAllEstados() {
		return estadoRepository.findAll();
	}
	
	
	public Estado findBySigla(String sigla) {
		Optional<Estado> estado = estadoRepository.findBysigla(sigla);
		
		return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Sigla: " + sigla + 
																	". Tipo: " + Estado.class.getName()));
		
	}
	
	public Estado findByNome(String nome) {
		Optional<Estado> estado = estadoRepository.findBynomeContains(nome);
		
		return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Nome: " + nome + 
																	". Tipo: " + Estado.class.getName()));
	}
	
	
	
	public Estado createEstado(Estado estado) {
		estado.setId(null);
		estadoRepository.save(estado);
		return estado;
	}
	
	
	public Estado updateEstado(Estado updateEstado) {
		Estado estado = findById(updateEstado.getId());
		
		estado.setNome(updateEstado.getNome());
		estado.setSigla(updateEstado.getSigla());
		
		return estadoRepository.save(estado);
	}
	
	
	public void deleteEstado(Integer id) {
		findById(id);
		
		try {
			estadoRepository.deleteById(id);
		}catch(DataIntegrityViolationException erro){
			throw new JoaoVFG.com.github.services.exception.DataIntegrityException
						("NAO E POSSIVEL EXCLUIR UM ESTADO QUE POSSUA CIDADES VINCULADAS");
			//futuro código para setar excluido = 1
		}
	}
	
	public Estado findBySiglaAux(String sigla) {
		try {
			return estadoRepository.findBySiglaAux(sigla);
		} catch (NullPointerException erro) {
			return null;
		}
	}
}
