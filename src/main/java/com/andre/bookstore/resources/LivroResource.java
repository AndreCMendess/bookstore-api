package com.andre.bookstore.resources;


import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.dtos.LivroDTO;
import com.andre.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
