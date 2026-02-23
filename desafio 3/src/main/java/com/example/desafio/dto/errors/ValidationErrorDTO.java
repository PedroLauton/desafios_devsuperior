package com.example.desafio.dto.errors;

import java.time.Instant;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO {

    private List<FieldMessageDTO> errors = new java.util.ArrayList<>();

    public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessageDTO(fieldName, message));
    }
}
