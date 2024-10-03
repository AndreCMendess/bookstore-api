package com.andre.bookstore.service;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.repositories.CategoriaRepository;
import com.andre.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class DBservice {


    // Injeção de dependência do repositório de Categoria , o spring cuida da criaçao da dependencia (O mesmo que categoriaRepositry = new CategoriaRepository())
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;



    public void instanciaBaseDeDados(){

        Categoria ca1 = new Categoria(null,"Informatica","Livro de Ti");
        Categoria ca2 = new Categoria(null,"Ficção Cientifica","Ficção Cientifica");
        Categoria ca3 = new Categoria(null,"Biografias","Livros de Biografias");


        Livro l1 = new Livro(null,"Clean Code","Robert Martin","Lorem ipsum",ca1);
        Livro l2 = new Livro(null,"Engenharia de Software","Louis V. Gerstner","Lorem ipsum",ca1);
        Livro l3 = new Livro(null,"The Time Machine","H.G. Wells","Lorem ipsum",ca2);
        Livro l4 = new Livro(null,"The war of the Worlds","H.G. Wells","Lorem ipsum",ca2);
        Livro l5 = new Livro(null,"I,Robot","Isaac Asimov","Lorem ipsum",ca2);



        ca1.getLivros().addAll(Arrays.asList(l1,l2));
        ca2.getLivros().addAll(Arrays.asList(l3,l4,l5));



        // Nas linhas abaixo os livros e as categorias sao salvos no banco de dados em memoria
        this.categoriaRepository.saveAll(Arrays.asList(ca1,ca2,ca3));
        this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5));

    }


}
