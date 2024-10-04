
package com.andre.bookstore.repositories;

import com.andre.bookstore.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,  Integer> {

    @Query("SELECT l FROM Livro l WHERE l.categoria.id = :id_cat ORDER BY l.titulo")
    List<Livro> findAllByCategoria(@Param(value = "id_cat") Integer id_cat);

}
