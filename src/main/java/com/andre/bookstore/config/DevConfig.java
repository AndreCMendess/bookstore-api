package com.andre.bookstore.config;

import com.andre.bookstore.service.DBservice;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBservice dBservice;

    //Com o uso da anotação value eu injeto valores ja configurados no properties ,
    // se o valor da  propiedade ser create o banco de dados sera criado

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @PostConstruct
    public boolean instanciaBaseDeDados(){

        if(strategy.equals("create")){
            this.dBservice.instanciaBaseDeDados();
        }

        return false;
    }
}
