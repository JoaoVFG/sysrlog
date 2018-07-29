package JoaoVFG.com.github.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.repositories.PessoaRepository;
import JoaoVFG.com.github.repositories.TipoPessoaRepository;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	TipoPessoaRepository tipoPessoaRepository;

	public Pessoa findById(Integer id) {
		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.buscaPorId(id));

		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Pessoa não encontrado! Id: " + id + ". Tipo: " + Pessoa.class.getName()));
	}

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public List<Pessoa> findByTipo(Integer tipo) {
		Optional<List<Pessoa>> pessoas = Optional.of(pessoaRepository.findBytipo(tipoPessoaRepository.findByid(tipo)));
		return pessoas.orElseThrow(() -> new ObjectNotFoundException(
				"Não existem pessoas para o tipo procurado! Id: " + tipo + ". Tipo: " + Pessoa.class.getName()));
	}

	public Pessoa findByCpf(String cpf) {
		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.findBycpf(cpf));
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Não existem pessoas para o tipo procurado! CPF: " + cpf + ". Tipo: " + Pessoa.class.getName()));
	}

	public Pessoa findByCnpj(String cnpj) {
		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.findBycnpj(cnpj));
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Não existem pessoas para o tipo procurado! CNPJ: " + cnpj + ". Tipo: " + Pessoa.class.getName()));
	}

	public List<Pessoa> findByrazaoSocial(String razaoSocial) {
		Optional<List<Pessoa>> pessoas = Optional.of(pessoaRepository.findByrazaoSocialContains(razaoSocial));
		return pessoas
				.orElseThrow(() -> new ObjectNotFoundException("Não existem pessoas para o tipo procurado! Razão: "
						+ razaoSocial + ". Tipo: " + Pessoa.class.getName()));
	}

	public Pessoa create(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa = pessoaRepository.save(pessoa);
		return findById(pessoa.getId());
	}

	public Pessoa updatePessoa(Pessoa updatePessoa) {
		Pessoa pessoa = findById(updatePessoa.getId());

		pessoa.setCnpj(updatePessoa.getCnpj());
		pessoa.setCpf(updatePessoa.getCpf());
		pessoa.setDataNascimento(updatePessoa.getDataNascimento());
		pessoa.setNome(updatePessoa.getNome());
		pessoa.setRazaoSocial(updatePessoa.getRazaoSocial());
		pessoa.setSexo(updatePessoa.getSexo());

		return pessoaRepository.save(pessoa);
	}

	public void deletarPessoa(Pessoa pessoa) {
		findById(pessoa.getId());

		try {
			pessoaRepository.deleteById(pessoa.getId());
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSA PESSOA.");
		}
	}

}
