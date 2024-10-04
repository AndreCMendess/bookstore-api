package com.andre.bookstore.service;

import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.repositories.LivroRepository;
import com.andre.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id){

        Optional<Livro> livro  = livroRepository.findById(id);
        return livro.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado id: " + id + ", Tipo: " + livro.getClass().getName()));

    }

    public List<Livro> findAll(Integer idCat) {
        categoriaService.findById(idCat);
        return livroRepository.findAllByCategoria(idCat);
    }

    public Livro update(Integer id, Livro livroAtualizado) {
        Livro livro = findById(id);
        updateData(livro,livroAtualizado);
        return livroRepository.save(livro);

    }

    private void updateData(Livro livro, Livro livroAtualizado) {
        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setNomeAutor(livroAtualizado.getNomeAutor());
        livro.setTexto(livroAtualizado.getTexto());
    }
}
