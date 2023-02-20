package com.integradora.gimnasio.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.integradora.gimnasio.dto.Mensaje;
import com.integradora.gimnasio.utils.Operations;

@RestControllerAdvice
public class GlobalExeption {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Mensaje> throwNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<Mensaje> throwAttributeException(AttributeException e) {
        return ResponseEntity.badRequest()
                .body(new Mensaje(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mensaje> generalException(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new Mensaje(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Mensaje> validationException(MethodArgumentNotValidException e){
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            messages.add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new Mensaje(HttpStatus.BAD_REQUEST, Operations.trimBrackets(messages.toString())));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Mensaje> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND,"not exists user"));
    }
}
