package com.example.abw.exception.validation;

import com.example.abw.entities.exception.ValidationError;
import lombok.Data;

@Data
public class ValidationException extends Exception {


    private ValidationError validationError;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, ValidationError validationError) {
        super(message);
        this.validationError = validationError;
    }

    public String getFullMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.getMessage()).append("\n");
        if (validationError != null) return stringBuilder.append(validationError.getErrors()).toString();
        else return stringBuilder.toString();
    }
}
