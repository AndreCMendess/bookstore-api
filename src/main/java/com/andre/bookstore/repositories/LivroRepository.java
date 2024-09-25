
package com.andre.bookstore.repositories;

import com.andre.bookstore.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,  Integer> {
    
}
