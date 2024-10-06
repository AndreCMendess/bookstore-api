package com.andre.bookstore.service;

import com.andre.bookstore.domain.Categoria;
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

    //Busca um livro no banco de dados pelo ID
    public Livro findById(Integer id){

        //Tenta encontrar um livro do banco de dados
        Optional<Livro> livro  = livroRepository.findById(id);
        //Retorna o livro encontrado, ou lança uma  exceção ObjectNotFoundException com uma mensagem personalizada
        return livro.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado id: " + id + ", Tipo: " + livro.getClass().getName()));

    }

    //Busca todos os livros  de determinada categoria especifica no banco de dados
    public List<Livro> findAll(Integer idCat) {
        //Verifica se a categoria existe
        categoriaService.findById(idCat);
        //Retorna todos os livros da categoria correspondente ao id
        return livroRepository.findAllByCategoria(idCat);
    }

    //Atualiza um livro existente
    public Livro update(Integer id, Livro livroAtualizado) {
        //Busca o livro pelo ID
        Livro livro = findById(id);
        //Atualiza os dados do livro com as informações fornecidas
        updateData(livro,livroAtualizado);
        //Salva as atualizações no banco de dados e retorna o livro atualizado
        return livroRepository.save(livro);
    }

    //Atualizar o livro com as novas informações
    private void updateData(Livro livro, Livro livroAtualizado) {
        //O titulo do livro recebe os dados do livro atualizado
        livro.setTitulo(livroAtualizado.getTitulo());
        //O nome do autor recebe os dados do livro atualizado
        livro.setNomeAutor(livroAtualizado.getNomeAutor());
        //O texto do livro recebe os dados do livro atualizado
        livro.setTexto(livroAtualizado.getTexto());
    }

    //Cria um novo livro
    public Livro create(Integer idCat, Livro livro) {
        //Define o id como null para que o banco de dados gere automaticamente
        livro.setId(null);
        //Verifica se a categoria informada existe
        Categoria cat = categoriaService.findById(idCat);
        //Atribui a categoria ao livro
        livro.setCategoria(cat);
        //Salva o  livro no banco de dados e o retorna
        return livroRepository.save(livro);

    }

    public void delete(Integer id) {
        Livro livro = findById(id);
        livroRepository.delete(livro);
    }
}
