package JoaoVFG.com.github.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.TipoPessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	@Transactional(readOnly = true)
	public Optional<List<Pessoa>> findBytipo(TipoPessoa tipoPessoa);
	
	@Transactional(readOnly = true)
	public Optional<Pessoa> findBycpf(String cpf);
	
	@Transactional(readOnly = true)
	public Optional<Pessoa> findBycnpj(String cnpj);
	
	@Transactional(readOnly = true)
	public Optional<List<Pessoa>> findByrazaoSocialContains(String razaoSocial);
	
	
}
