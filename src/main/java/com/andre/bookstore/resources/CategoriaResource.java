package com.andre.bookstore.resources;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.dtos.CategoriaDTO;
import com.andre.bookstore.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value="/{id}")// Define que ese metodo é uma requisição GET com um id na url
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){// Recebe o id como parametro e retorna a categoria correspondente

      // Busca uma categoria no banco de dados pelo id informado.
      Categoria obj = service.findById(id);
      // Retorna uma resposta com o status 200 (ok), e a categoria encontrada no corpo da resposta
      return  ResponseEntity.ok().body(obj);
    }

    @GetMapping// Define que esse metodo é uma requisição Get
    public ResponseEntity<List<CategoriaDTO>> findAll(){//Retorna uma lista de CategoriaDTO
        // Obtém a lista  de todas as categorias do banco de dados
        List<Categoria> list = service.findAll();
        // Converte cada objeto da lista de Categoria em CategoriaDTO usando stream
        List<CategoriaDTO> listDTO = list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        // Retorna uma resposta com o status 200 (ok), e a lista de categorias (listDTO) no corpo da resposta
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping// Define que este metodo é uma requisição post (create).
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria obj){//Recebe um objeto categoria na forma de um request json
           //Cria a categoria no banco de dados e atualiza a variavel obj com ela
           obj = service.create(obj);
           //Cria uma URI que aponta pro recurso criado , incluindo o id da categoria
           URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
           // retorna uma resposta com o status 201 (created) e a URI de um novo recurso criado
           // Se fosse .body(obj) retornaria a resposta status 201 e o objeto Categoria ja criado no corpo da resposta
           return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")// Define que este metodo responde a requisições PUT na URL com um ID
    public ResponseEntity<CategoriaDTO> update (@PathVariable Integer id,@Valid @RequestBody CategoriaDTO objDto){ // atualiza uma categoria com base no id e nos dados do objDto

        Categoria newObj = service.create(id,objDto);// recebe o obj atualizado pelo serviço
        return ResponseEntity.ok().body(new CategoriaDTO(newObj));// retorna a reposta http 200 com o corpo da resposta a categoria atualizada
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}