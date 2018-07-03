package JoaoVFG.com.github.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.domain.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	//Busca Estado por Sigla
	@Transactional(readOnly = true)
	public Optional<Estado> findBysigla(String sigla);
	
	//Busca Estado por Nome
	@Transactional(readOnly = true)
	public Optional<Estado> findBynomeContains(String nome);
	
}
