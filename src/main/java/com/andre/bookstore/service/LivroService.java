package com.andre.bookstore.service;

import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.repositories.LivroRepository;
import com.andre.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro findById(Integer id){

        Optional<Livro> livro  = livroRepository.findById(id);
        return livro.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado id: " + id + ", Tipo: " + livro.getClass().getName()));

    }
}
