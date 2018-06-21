package JoaoVFG.com.github.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.domain.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	//Busca Estado por Sigla
	@Transactional(readOnly = true)
	public Estado findBysigla(String sigla);
	
	//Busca Estado por Nome
	@Transactional(readOnly = true)
	public Estado findBynomeContains(String nome);
	
}
