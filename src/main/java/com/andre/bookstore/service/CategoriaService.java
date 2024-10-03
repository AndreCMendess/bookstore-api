package com.andre.bookstore.service;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.exceptions.ObjectNotFoundException;
import com.andre.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository repository;

    public Categoria findById(Integer id){

        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));

    }

}
