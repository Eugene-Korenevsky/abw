package com.example.abw.entities.exception;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ValidationError {
    private ArrayList<String> errors = new ArrayList<>();
    private String EntityName;

    public String getErrors() {
        int size = 1;
        StringBuilder stringBuilder = new StringBuilder();
        if (EntityName != null) {
            stringBuilder.append(EntityName).append("\n");
        }
        for (String error : errors) {
            stringBuilder.append(size).append(". ").append(error).append("\n");
            size++;
        }
        return stringBuilder.toString();
    }

    public void addError(String error) {
        errors.add(error);
    }
}
