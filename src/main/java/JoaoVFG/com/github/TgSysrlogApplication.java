package JoaoVFG.com.github;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JoaoVFG.com.github.service.route.CalculaDistancia;


@SpringBootApplication
public class TgSysrlogApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(TgSysrlogApplication.class, args);
		
		CalculaDistancia calculaDistancia = new CalculaDistancia();
		
		calculaDistancia.calcDistancia("12288560", "12285020");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
