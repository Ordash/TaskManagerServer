package com.esas.taskmanager.util;

import org.springframework.validation.FieldError;

public class ValidationFailureResponse {
    private FieldError[] fieldErrors;

    public ValidationFailureResponse(FieldError[] fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
