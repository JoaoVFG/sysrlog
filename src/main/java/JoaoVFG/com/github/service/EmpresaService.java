package JoaoVFG.com.github.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.Empresa;
import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.repositories.EmpresaRepository;
import JoaoVFG.com.github.repositories.TipoEmpresaRepository;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class EmpresaService {

	@Autowired
	TipoEmpresaRepository tipoEmpresaRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	PessoaService pessoaService;

	public Empresa findById(Integer id) {
		Optional<Empresa> empresa = Optional.ofNullable(empresaRepository.buscaPorId(id));
		return empresa.orElseThrow(() -> new ObjectNotFoundException(
				"Empresa não encontrado! Id: " + id + ". Tipo: " + Pessoa.class.getName()));
	}

	public List<Empresa> findAll() {
		return empresaRepository.findAll();
	}

	public Empresa findByIdPessoa(Integer id) {
		Pessoa pessoa = pessoaService.findById(id);
		Optional<Empresa> empresa = Optional.ofNullable(empresaRepository.findBypessoa(pessoa));
		return empresa
				.orElseThrow(() -> new ObjectNotFoundException("Não houverem resultados para a restrição de pesquisa"));
	}

	public List<Empresa> findByTipoEmpresa(Integer id) {
		Optional<List<Empresa>> empresas = Optional
				.ofNullable(empresaRepository.findBytipoEmpresa(tipoEmpresaRepository.buscaPorId(id)));
		return empresas
				.orElseThrow(() -> new ObjectNotFoundException("Não houverem resultados para a restrição de pesquisa"));
	}
	
	public List<Empresa> findTransportadoras(){
		Optional<List<Empresa>> empresas = Optional
				.ofNullable(empresaRepository.findByTransportadora(1));
		return empresas
				.orElseThrow(() -> new ObjectNotFoundException("Não houverem resultados para a restrição de pesquisa"));
	}
	
	public List<Empresa> findByMatriz(Integer id){
		Optional<List<Empresa>> empresas = Optional
				.ofNullable(empresaRepository.findByTransportadora(id));
		return empresas
				.orElseThrow(() -> new ObjectNotFoundException("Não houverem resultados para a restrição de pesquisa"));
	}
	
	public Empresa createEmpresa(Empresa empresa) {
		if(findByIdPessoa(empresa.getPessoa().getId()) == null) {
			empresa.setId(null);
			empresa = empresaRepository.save(empresa);
			return findById(empresa.getId());
		}else {
			throw new DataIntegrityException("NÃO É POSSIVEL CADASTRAR ESSE EMPRESA, POIS ELA JA ESTA CADASTRADA");
		}
	}
	
	public Empresa updateEmpresa(Empresa empresaUpdate) {
		Empresa empresa = findById(empresaUpdate.getId());
		if(!(empresa == null)) {
			empresa.setPessoa(empresaUpdate.getPessoa());
			empresa.setTipoEmpresa(empresaUpdate.getTipoEmpresa());
			empresa.setTransportadora(empresaUpdate.getTransportadora());
			empresa.setEmpresaMatrizId(empresaUpdate.getEmpresaMatrizId());
			
			return empresaRepository.save(empresa);
		}else {
			throw new ObjectNotFoundException("Não houverem resultados para a restrição de pesquisa");
		}
	}
	
	public void deletarEmpresa(Empresa empresa) {
		findById(empresa.getId());

		try {
			empresaRepository.deleteById(empresa.getId());
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSA PESSOA.");
		}
	}
}
