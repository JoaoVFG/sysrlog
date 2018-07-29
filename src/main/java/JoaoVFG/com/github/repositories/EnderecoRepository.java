package JoaoVFG.com.github.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.Cep;
import JoaoVFG.com.github.entity.Endereco;
import JoaoVFG.com.github.entity.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	@Transactional(readOnly = true)
	Optional<Endereco> findBypessoa(Optional<Pessoa> Pessoa);
	
	@Transactional(readOnly = true)
	Optional<List<Endereco>> findBycep(Optional<Cep> cep);
	
	
}
