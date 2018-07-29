package JoaoVFG.com.github.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JoaoVFG.com.github.entity.TipoPessoa;

@Repository
public interface TipoPessoaRepository extends JpaRepository<TipoPessoa, Integer>{
	
	TipoPessoa findBydescricao(String descricao);
	
	TipoPessoa findByid(Integer id);
}
