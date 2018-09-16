package JoaoVFG.com.github.service.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.dto.request.insert.InsertLoginDTO;
import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.security.Role;
import JoaoVFG.com.github.entity.security.User;
import JoaoVFG.com.github.repositories.security.UserRepository;
import JoaoVFG.com.github.service.PessoaService;
import JoaoVFG.com.github.service.utils.GenerateRandom;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PessoaService pessoaService;

	@Autowired
	PasswordEncoder encoder;

	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"User não encontrado! Id: " + id + ". Tipo: " + User.class.getName()));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByEmail(String email) {
		Optional<User> user = Optional.ofNullable(userRepository.findByemail(email));
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"User não encontrado! Email: " + email + ". Tipo: " + User.class.getName()));
	}

	public List<User> findByEmpresa(Integer idEmpresa) {
		Optional<List<User>> users = Optional.ofNullable(userRepository.findUsersByEmpresa(idEmpresa));
		return users.orElseThrow(() -> new ObjectNotFoundException(
				"Usuários não encontrados! Empresa: " + idEmpresa + ". Tipo: " + User.class.getName()));
	}
	
	public User findByApiKey(String apiKey){
		Optional<User> user = Optional.ofNullable(userRepository.findByapiKey(apiKey));
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"User não encontrado com essa chave de acesso. Chave: " + apiKey + ". Tipo: " + User.class.getName()));
	}

	public User createUser(InsertLoginDTO insertLoginDTO) {
		Pessoa pessoa = pessoaService.findById(insertLoginDTO.getIdPessoa());
		User user = new User();
		if (userRepository.findByemail(insertLoginDTO.getEmail()) == null) {

			user.setEmail(insertLoginDTO.getEmail());
			user.setSenha(encoder.encode(insertLoginDTO.getSenha()));
			user.setPessoa(pessoa);
			// Se for pessoa juridica da todas as permissões
			if (pessoa.getTipo().getId() == 2) {
				Set<Role> roles = new HashSet<>(roleService.findAll());
				// remove Permissão de administrador
				roles.remove(roleService.findById(1));
				user.setRoles(roles);
			}else {
				user.setRoles(returnDefaultsRoles());
			}
			
			user.setApiKey(generateRandomApiKey());
			
			userRepository.save(user);
			
			
		} else {
			throw new DataIntegrityException("NÃO É POSSIVEL CADASTRAR ESSE LOGIN");
		}

		return user;
	}

	public void deletaUser(Integer userId) {
		findById(userId);
		try {
			userRepository.deleteById(userId);
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE USUÁRIO.");
		}
	}

	public User updateUser(User updateUser) {
		User user = userRepository.buscaPorId(updateUser.getId());

		user.setEmail(updateUser.getEmail());
		user.setSenha(encoder.encode(updateUser.getSenha()));
		user.setRoles(updateUser.getRoles());

		return user;
	}
	
	public Set<Role> returnDefaultsRoles(){
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findById(33));
		roles.add(roleService.findById(34));
		roles.add(roleService.findById(35));
		roles.add(roleService.findById(36));
		roles.add(roleService.findById(37));
		roles.add(roleService.findById(38));
		roles.add(roleService.findById(27));
		roles.add(roleService.findById(25));
		return roles;
	}
	
	public String generateRandomApiKey() {
		GenerateRandom gr = new GenerateRandom();
		return gr.newRandom(30);
	}
}
