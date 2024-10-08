package com.andre.bookstore.resources.exceptions;


import com.andre.bookstore.service.exceptions.DataIntegrityViolationException;
import com.andre.bookstore.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
            StandardError error = new StandardError(e.getMessage(), System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
        StandardError error = new StandardError(e.getMessage(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler( MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError( MethodArgumentNotValidException e, ServletRequest request){
       ValidationError error = new ValidationError("Erro na validação dos campos " , System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
       for(FieldError x : e.getBindingResult().getFieldErrors()) {
            error.addErros(x.getField(),x.getDefaultMessage());

       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
