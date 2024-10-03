package com.andre.bookstore.config;

import com.andre.bookstore.service.DBservice;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//Criação de uma classe externa para inicializar o banco de dados ,separação de responsabilidade ,facilidade de testes e  reutilização de codigo.
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dBservice;

    @PostConstruct
    public void instanciaBaseDeDados(){
        this.dBservice.instanciaBaseDeDados();
    }

}
