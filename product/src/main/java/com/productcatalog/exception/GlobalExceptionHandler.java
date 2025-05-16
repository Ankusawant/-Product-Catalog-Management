package com.productcatalog.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherErrors(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}