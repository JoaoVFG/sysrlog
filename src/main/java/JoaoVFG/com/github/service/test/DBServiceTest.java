package JoaoVFG.com.github.service.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.Endereco;
import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.Telefone;
import JoaoVFG.com.github.repositories.EnderecoRepository;
import JoaoVFG.com.github.repositories.PessoaRepository;
import JoaoVFG.com.github.repositories.TelefoneRepository;
import JoaoVFG.com.github.repositories.TipoPessoaRepository;
import JoaoVFG.com.github.service.CepService;
import JoaoVFG.com.github.service.consultaCep.CreateCep;

@Service
public class DBServiceTest {

	@Autowired
	CepService cepService;

	@Autowired
	CreateCep createCep;

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	TipoPessoaRepository tipo;

	@Autowired
	TelefoneRepository telefoneRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	public void instantiateTesteDataBase() {

		createCep.generateCep("12288560");
		createCep.generateCep("12281350");
		createCep.generateCep("12285020");
		createCep.generateCep("12295370");
		createCep.generateCep("12281460");
		createCep.generateCep("12288460");
		createCep.generateCep("12281420");

		Pessoa pessoaf1 = new Pessoa(null, tipo.findByid(1), "JV", "45567860889", "21/07/1996", "M");
		Pessoa pessoaf2 = new Pessoa(null, tipo.findByid(1), "JJ", "11593054807", "01/11/1967", "M");
		Pessoa pessoaf3 = new Pessoa(null, tipo.findByid(1), "Ingrid", "89988998899", "11/08/1993", "F");
		Pessoa pessoaf4 = new Pessoa(null, tipo.findByid(1), "carla", "58963221474", "13/04/1970", "F");
		Pessoa pessoaj1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans A", "4478969850008");
		Pessoa pessoaj2 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans B", "4485478220008");
		Pessoa pessoaj3 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A", "485874810008");
		Pessoa pessoaj4 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "mandou X", "898725950008");
		pessoaRepository
				.saveAll(Arrays.asList(pessoaf1, pessoaf2, pessoaf3, pessoaf4, pessoaj1, pessoaj2, pessoaj3, pessoaj4));

		Telefone tel1 = new Telefone(null, "Celular", "12991157861", pessoaf1);
		Telefone tel2 = new Telefone(null, "Celular", "12996260032", pessoaf1);
		Telefone tel3 = new Telefone(null, "Celular", "12991450102", pessoaf2);
		Telefone tel4 = new Telefone(null, "Celular", "12981345477", pessoaf3);
		Telefone tel5 = new Telefone(null, "Celular", "12991292612", pessoaf4);
		Telefone tel6 = new Telefone(null, "Fixo", "1236525626", pessoaf4);
		Telefone tel7 = new Telefone(null, "Fixo", "1236524048", pessoaf4);
		Telefone tel8 = new Telefone(null, "Fixo", "1185988587", pessoaj2);
		Telefone tel9 = new Telefone(null, "Fixo", "1188996633", pessoaj2);
		Telefone tel10 = new Telefone(null, "Celular", "11955889966", pessoaj3);
		Telefone tel11 = new Telefone(null, "Fixo", "1256566336", pessoaj4);
		Telefone tel12 = new Telefone(null, "celular", "12988774455", pessoaj4);

		telefoneRepository
				.saveAll(Arrays.asList(tel1, tel2, tel3, tel4, tel5, tel6, tel7, tel8, tel9, tel10, tel11, tel12));

		Endereco end1 = new Endereco(null, pessoaf1, cepService.findByCep("12288560"), 718, "");
		Endereco end2 = new Endereco(null, pessoaf2, cepService.findByCep("12288560"), 718, "");
		Endereco end3 = new Endereco(null, pessoaf4, cepService.findByCep("12288560"), 718, "");
		Endereco end4 = new Endereco(null, pessoaf3, cepService.findByCep("12290379"), 50, "");
		Endereco end5 = new Endereco(null, pessoaj1, cepService.findByCep("12289368"), 30, "");
		Endereco end6 = new Endereco(null, pessoaj1, cepService.findByCep("12289085"), 127, "");
		Endereco end7 = new Endereco(null, pessoaj1, cepService.findByCep("12287360"), 88, "");
		Endereco end8 = new Endereco(null, pessoaj1, cepService.findByCep("12216300"), 50, "");

		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5, end6, end7, end8));

	}

}
