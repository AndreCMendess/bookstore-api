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

    @GetMapping(value="/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){

        Livro livro = service.findById(id);
        return ResponseEntity.ok().body(livro);


    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value= "categoria",defaultValue = "0") Integer id_cat){
        List<Livro> livros = service.findAll(id_cat);
        List<LivroDTO> livrosDTO = livros.stream().map(LivroDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(livrosDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro){
        Livro livroAtualizado = service.update(id,livro);
        return ResponseEntity.ok().body(livroAtualizado);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro livro){
        Livro livroAtualizado = service.update(id,livro);
        return ResponseEntity.ok().body(livroAtualizado);

    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0")  Integer id_cat , @RequestBody Livro livro ){
        Livro novoLivro = service.create(id_cat,livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}
