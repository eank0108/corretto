package com.ean.corretto.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e){
        return ResponseEntity.status(e.getCode())
                .body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(450)
                .body(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
