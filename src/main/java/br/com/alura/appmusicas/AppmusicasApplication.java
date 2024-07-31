package br.com.alura.appmusicas;

import br.com.alura.appmusicas.principal.Principal;
import br.com.alura.appmusicas.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppmusicasApplication implements CommandLineRunner {

	@Autowired
	ArtistaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AppmusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}
