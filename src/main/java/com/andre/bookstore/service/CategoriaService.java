package com.andre.bookstore.service;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.dtos.CategoriaDTO;
import com.andre.bookstore.repositories.CategoriaRepository;
import com.andre.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository repository;

    // Metodo que retorna uma categoria procurada por id
    public Categoria findById(Integer id){

        // Busca a categoria pelo id no banco de dados
        Optional<Categoria> obj = repository.findById(id);
        // Retorna a categoria ou lança uma exceção ObjectNotFoundException se não encontrada
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));

    }

    // Metodo que retorna todas as categorias do banco de dados
    public List<Categoria> findAll(){
      return  repository.findAll();
    }

    // Metodo que cria uma categoria
    public Categoria create(Categoria obj){
        // Garante que o id seja nulo , indicando que é um novo registro
        obj.setId(null);
        // Salva a nova categoria no banco de dados e retorna a categoria salva
        return repository.save(obj);
    }


    // Metodo que atualiza uma categoria no banco de dados
    public Categoria create(Integer id, CategoriaDTO objDto) {
        // Verifica se a categoria  existe no banco de dados por ID
        Categoria obj = findById(id);
        // Atualiza o nome da categoria com os dados fornecidos no DTO
        obj.setNome(objDto.getNome());
        // Atualiza a descrição da categoria com os dados fornecidos no DTO
        obj.setDescricao(objDto.getDescricao());
        // Salva as alterações no banco de dados e retorna a categoria atualizada
        return repository.save(obj);

    }
}
