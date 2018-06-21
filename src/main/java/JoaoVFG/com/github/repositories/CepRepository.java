package JoaoVFG.com.github.repositories;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.domain.Cep;

public interface CepRepository extends JpaRepository<Cep, Integer>{
	
	//busca Cep por Cep
	@Transactional(readOnly = true)
	public Cep findBycep(Integer cep);
	
	//busca Cep por nome Rua
	@Transactional(readOnly = true)
	public LinkedList<Cep> findBynomeRua(String nomeRua);
	
	
}
