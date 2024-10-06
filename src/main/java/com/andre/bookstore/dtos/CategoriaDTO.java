package com.andre.bookstore.dtos;

import com.andre.bookstore.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CategoriaDTO {

    private Integer id;
    @NotEmpty(message = "Campo Nome é requerido")
    @Length(min = 3 , max = 100 , message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotEmpty(message = "Campo Descrição é requerido")
    @Length(min = 3 , max = 200 , message = "O campo Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
    }

    public CategoriaDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
