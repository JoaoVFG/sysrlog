package JoaoVFG.com.github.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.domain.Cidade;
import JoaoVFG.com.github.repositories.CidadeRepository;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	
	@Autowired(required = true)
	CidadeRepository cidadeRepository;
	
	@Autowired(required = true)
	EstadoService estadoService;
	
	
	public Cidade findById(Integer id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		System.out.println("teste");
		
		return cidade.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada! Id: " + id + 
																	". Tipo: " + Cidade.class.getName()));
	}
	
	
	public List<Cidade> findAll(){
		return cidadeRepository.findAll();
	}
	
	
	public LinkedList<Cidade> findByNome(String nome){
		Optional<LinkedList<Cidade>> cidades = cidadeRepository.findByNomeContains(nome);
		
		return cidades.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada! Nome Pesquisado: " + nome + 
																	 ". Tipo: " + Cidade.class.getName()));
	}
	
	
	public LinkedList<Cidade> findByEstado(String sigla){
		Optional<LinkedList<Cidade>> cidades = cidadeRepository.findByEstado(estadoService.findBySigla(sigla));
		
		return cidades.orElse(null);
	}
	
	
	public LinkedList<Cidade> findByNomeCidadeEstadoSigla(String nomeCidade, String siglaEstado){
		Optional<LinkedList<Cidade>> cidades = cidadeRepository.findCidadesEstadoIdNomeCidades
																			(estadoService.findBySigla(siglaEstado).getId(), 
																					nomeCidade);
		return cidades.orElseThrow(() -> new ObjectNotFoundException("Não foram encontradas cidades para essa restrição de pesquisa" + 
				  													". Tipo: " + Cidade.class.getName()));
	}
	
	
	public Cidade createCidade(Cidade cidade){
		cidade.setId(null);
		cidade = cidadeRepository.save(cidade);
		return findById(cidade.getId());
	}
	
	
	public Cidade updateCidade(Cidade updateCidade) {
		Cidade cidade = findById(updateCidade.getId());
		
		cidade.setNome(updateCidade.getNome());
		cidade.setEstado(updateCidade.getEstado());
		
		return cidadeRepository.save(cidade);
		
	}
	
	
	public void DeletarCidade(Integer id) {
		findById(id);
		
		try {
			cidadeRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR UMA CIDADE QUE POSSUA CEPs VINCULADOS");
			//futuro código para setar excluido = 1
		}
	}
	
	
	public Cidade findByNomeCidadeSiglaEstadoAux( String siglaEstado , String nomeCidade) {
		try {
			return cidadeRepository.findCidadesEstadoIdNomeCidadesAux(estadoService.findBySigla(siglaEstado).getId(), nomeCidade);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
}