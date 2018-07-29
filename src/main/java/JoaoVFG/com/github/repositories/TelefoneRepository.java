package JoaoVFG.com.github.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer>{
	
	@Transactional(readOnly = true)
	public Optional<List<Telefone>> findBypessoa(Optional<Pessoa> pessoa);
	
	@Transactional(readOnly = true)
	@Query("SELECT telefone FROM Telefone telefone WHERE telefone.tipoNumero = :tipo AND telefone.pessoa.id = :pessoaId")
	public Optional<List<Telefone>> findBypessoaAndtipoNumero(@Param("pessoaId")Integer pessoaId, @Param("tipo")String tipoNumero);

}
