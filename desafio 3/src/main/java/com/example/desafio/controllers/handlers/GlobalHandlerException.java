package com.example.desafio.controllers.handlers;

import com.example.desafio.dto.errors.CustomErrorDTO;
import com.example.desafio.dto.errors.ValidationErrorDTO;
import com.example.desafio.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorDTO> handleException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
