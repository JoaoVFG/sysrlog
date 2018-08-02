package JoaoVFG.com.github.service.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.Empresa;
import JoaoVFG.com.github.entity.Endereco;
import JoaoVFG.com.github.entity.Pessoa;
import JoaoVFG.com.github.entity.Telefone;
import JoaoVFG.com.github.repositories.EmpresaRepository;
import JoaoVFG.com.github.repositories.EnderecoRepository;
import JoaoVFG.com.github.repositories.PessoaRepository;
import JoaoVFG.com.github.repositories.TelefoneRepository;
import JoaoVFG.com.github.repositories.TipoEmpresaRepository;
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

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	TipoEmpresaRepository tipoEmpresaRepository;

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
		Pessoa pessoaj1_0 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans A", "4478969850008");
		Pessoa pessoaj1_1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans A unidade 2", "4478969850009");
		Pessoa pessoaj1_2 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans A unidade 3", "4478969850010");
		Pessoa pessoaj2_0 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans B", "4485478220008");
		Pessoa pessoaj2_1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "trans B unidade 2", "4485478220009");
		Pessoa pessoaj3_1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A", "485874810008");
		Pessoa pessoaj3_2 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A unidade 2", "485874810009");
		Pessoa pessoaj3_3 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A unidade 3", "485874810018");
		Pessoa pessoaj3_4 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "ENviadora A unidade 4", "485874810088");
		Pessoa pessoaj4_0 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "mandou X", "898725950008");
		Pessoa pessoaj4_1 = new Pessoa(null, tipo.findBydescricao("JURIDICA"), "mandou X 2 ", "4478969850008");
		pessoaRepository.saveAll(
				Arrays.asList(pessoaf1, pessoaf2, pessoaf3, pessoaf4, pessoaj1_0, pessoaj1_2, pessoaj1_1, pessoaj2_0,
						pessoaj2_1, pessoaj3_4, pessoaj3_1, pessoaj3_2, pessoaj3_3, pessoaj4_0, pessoaj4_1));

		Telefone tel1 = new Telefone(null, "Celular", "12991157861", pessoaf1);
		Telefone tel2 = new Telefone(null, "Celular", "12996260032", pessoaf1);
		Telefone tel3 = new Telefone(null, "Celular", "12991450102", pessoaf2);
		Telefone tel4 = new Telefone(null, "Celular", "12981345477", pessoaf3);
		Telefone tel5 = new Telefone(null, "Celular", "12991292612", pessoaf4);
		Telefone tel6 = new Telefone(null, "Fixo", "1236525626", pessoaf4);
		Telefone tel7 = new Telefone(null, "Fixo", "1236524048", pessoaf4);
		Telefone tel8 = new Telefone(null, "Fixo", "1185988587", pessoaj2_1);
		Telefone tel9 = new Telefone(null, "Fixo", "1188996633", pessoaj2_0);
		Telefone tel10 = new Telefone(null, "Celular", "11955889966", pessoaj3_1);
		Telefone tel11 = new Telefone(null, "Fixo", "1256566336", pessoaj4_1);
		Telefone tel12 = new Telefone(null, "Celular", "12988774455", pessoaj4_0);

		telefoneRepository
				.saveAll(Arrays.asList(tel1, tel2, tel3, tel4, tel5, tel6, tel7, tel8, tel9, tel10, tel11, tel12));

		Endereco end1 = new Endereco(null, pessoaf1, cepService.findByCep("12288560"), 718, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________1");
		Endereco end2 = new Endereco(null, pessoaf2, cepService.findByCep("12288560"), 718, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________2");
		Endereco end3 = new Endereco(null, pessoaf4, cepService.findByCep("12288560"), 718, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________3");
		Endereco end4 = new Endereco(null, pessoaf3, cepService.findByCep("12290379"), 50, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________4");
		Endereco end5 = new Endereco(null, pessoaj1_0, cepService.findByCep("12289368"), 30, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________5");
		Endereco end6 = new Endereco(null, pessoaj4_0, cepService.findByCep("12289085"), 127, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________6");
		Endereco end7 = new Endereco(null, pessoaj2_0, cepService.findByCep("12287360"), 88, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________7");
		Endereco end8 = new Endereco(null, pessoaj3_1, cepService.findByCep("12216300"), 666, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________8");
		Endereco end9 = new Endereco(null, pessoaj1_1, cepService.findByCep("04021001"), 1, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________9");
		Endereco end10 = new Endereco(null, pessoaj2_1, cepService.findByCep("12209060"), 15, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________10");
		Endereco end11 = new Endereco(null, pessoaj3_2, cepService.findByCep("12010070"), 1654, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________11");
		Endereco end12 = new Endereco(null, pessoaj3_3, cepService.findByCep("12500140"), 898, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________12");
		Endereco end13 = new Endereco(null, pessoaj3_4, cepService.findByCep("12301040"), 15, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________13");
		Endereco end14 = new Endereco(null, pessoaj4_1, cepService.findByCep("11660070"), 507, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________14");
		Endereco end15 = new Endereco(null, pessoaj1_2, cepService.findByCep("12209310"), 502, "");
		System.out.println("EUHEUHEUHEUHEUHEUHEUEUEHUE_________________________________15");
	

		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5, end6, end7, end8, end9, end10, end11,
				end12, end13, end14, end15));

		Empresa empresa1 = new Empresa(null, pessoaj1_0, tipoEmpresaRepository.buscaPorId(1), 1, null);
		Empresa empresa2 = new Empresa(null, pessoaj2_0, tipoEmpresaRepository.buscaPorId(1), 1, null);
		Empresa empresa3 = new Empresa(null, pessoaj3_1, tipoEmpresaRepository.buscaPorId(1), 1, null);
		Empresa empresa4 = new Empresa(null, pessoaj4_0, tipoEmpresaRepository.buscaPorId(1), 1, null);
		Empresa empresa5 = new Empresa(null, pessoaj1_1, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj1_0.getId());
		Empresa empresa6 = new Empresa(null, pessoaj1_2, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj1_0.getId());
		Empresa empresa7 = new Empresa(null, pessoaj2_1, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj2_0.getId());
		Empresa empresa8 = new Empresa(null, pessoaj3_2, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj3_1.getId());
		Empresa empresa9 = new Empresa(null, pessoaj3_3, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj3_1.getId());
		Empresa empresa10 = new Empresa(null, pessoaj3_4, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj3_1.getId());
		Empresa empresa11 = new Empresa(null, pessoaj4_1, tipoEmpresaRepository.buscaPorId(2), 1, pessoaj4_0.getId());

		empresaRepository.saveAll(Arrays.asList(empresa1, empresa2, empresa3, empresa4, empresa5, empresa6, empresa7,
				empresa8, empresa9, empresa10, empresa11));
		
		
	}

}
