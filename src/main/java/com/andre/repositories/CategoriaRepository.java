
package com.andre.repositories;

import com.andre.bookstore.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/* Criação de uma interface repository que extende o JpaRepository,
como primeiro argumento espera a classe que sera implementada o repository ,
a classe que sera feita a persistencia de informações e camada de informações.
como segundo argumento ele espera o tipo primitivo do identificador da classe.*/

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
//    
    
}
