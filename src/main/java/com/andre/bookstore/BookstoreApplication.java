package com.andre.bookstore;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.repositories.CategoriaRepository;
import com.andre.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication  //Essa anotação marca a classe como a principal da aplicação Spring
public class BookstoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Injeção de dependência do repositório de Categoria , o spring cuida da criaçao da dependencia (O mesmo que categoriaRepositry = new CategoriaRepository())
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LivroRepository livroRepository;

	//O metodo é executado ao iniciar a aplição , instancia categoria e livros e os vincula
	@Override
	public void run(String... args) throws Exception {
		Categoria ca1 = new Categoria(null,"Informatica","Livro de Ti");
		Livro l1 = new Livro(null,"Clean Code","Robert Martin","Lorem ipsum",ca1);
		ca1.getLivros().addAll(Arrays.asList(l1));

		// Nas linhas abaixo os livros e as categorias sao salvos no banco de dados em memoria
		this.categoriaRepository.saveAll(Arrays.asList(ca1));
		this.livroRepository.saveAll(Arrays.asList(l1));
	}
}
