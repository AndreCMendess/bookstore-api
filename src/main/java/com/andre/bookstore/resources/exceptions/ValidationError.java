package com.andre.bookstore.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(String error, long timestamp, Integer status) {
        super(error, timestamp, status);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addErros(String fieldName,String message) {
        this.erros.add(new FieldMessage(fieldName,message));
    }
}
