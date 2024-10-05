package com.andre.bookstore.resources;


import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.dtos.LivroDTO;
import com.andre.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    //Metodo que busca um livro no banco de dados utilizando o ID fornecido na URL
    @GetMapping(value="/{id}")//Define uma rota GET que recebe o ID do livro como um parametro na URL, por exemplo: localhost:8080/livro/1
    public ResponseEntity<Livro> findById(@PathVariable Integer id){

        //Chama o service para buscar o livro no banco de dados com base no ID fornecido
        Livro livro = service.findById(id);
        //Retorna uma resposta HTTP com o status 200 (ok) contendo no corpo da resposta o objeto Livro encontrado
        return ResponseEntity.ok().body(livro);


    }

    //Metodo que retorna todos os livros pertencentes a  uma determinada categoria
    @GetMapping//Define que este metodo responde a requisicoes HTTP do tipo GET
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value= "categoria",defaultValue = "0") Integer id_cat){// Extrai o parâmetro "categoria" da URL como uma query string
        //Chama o service para para buscar todos os livros relacionados a categoria fornecida pelo id_cat
        //Caso nenhum ID de categoria seja fornecido ,o valor padrao é 0
        List<Livro> livros = service.findAll(id_cat);
        //Converte a lista de objetos Livro para uma lista de objetos LivroDTO usando o metodo Stream()
        //LivroDTO é uma versão simplificada do Livro para  transferencia de dados (DTO Data-Transfer Object)
        List<LivroDTO> livrosDTO = livros.stream().map(LivroDTO::new).collect(Collectors.toList());
        //Retorna uma respota HTTP com o status 200() contendo no corpo da resposta o objeto livrosDTO
        return ResponseEntity.ok().body(livrosDTO);

    }

    //Metodo que atualiza um livro escolhido por id
    @PutMapping(value = "/{id}")//Define que esse metodo responde a requisicoes HTTP do tipo Put e espera como valor um ID
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro){//@PathVariable captura o ID da URL. @RequestBody captura os dados do livro no corpo da requisição
        //Atualiaz o livro no banco de dados com base no ID e nos dados fornecidos no corpo da requisição
        Livro livroAtualizado = service.update(id,livro);
        //Retorna uma respota HTTP com o status 200() e o livro atualizado no corpo da resposta
        return ResponseEntity.ok().body(livroAtualizado);

    }

    // Metodo que atualiza parcialmente um livro pelo ID fornecido na URL
    @PatchMapping(value = "/{id}")// Responde a requisições do tipo Patch, com um ID na URL
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro livro){//@PathVariable captura o ID da URL. @RequestBody recebe os dados do livro no corpo da requisição
        // Atualiza parcialmente o livro no banco de dados com base no ID e nos dados fornecidos no corpo da requisição
        Livro livroAtualizado = service.update(id,livro);
        // Retorna uma resposta HTTP com status 200 (OK) e o livro atualizado no corpo da resposta
        return ResponseEntity.ok().body(livroAtualizado);

    }

    // Metodo que cria um novo  livro e o salva no banco de dados
    @PostMapping  // Define que o metodo responde a requisições HTTP do tipo POST
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0")  Integer id_cat , @RequestBody Livro livro ){//@RequestParam captura o ID da categoria da URL, com 0 como valor padrao. @RequestBody mapeia os dados do livro do corpo da requisição
        //Cria um novo livro no banco de dados , associando-o a categoria  indicada e usando os dados enviados no corpo da requisição
        Livro novoLivro = service.create(id_cat,livro);
        // Gera a URI do novo livro criado com o ID gerado automaticamente
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();
        // Retorna uma resposta HTTP com o status 201(created) e a URI do novo livro criado
        return ResponseEntity.created(uri).build();
    }



}
