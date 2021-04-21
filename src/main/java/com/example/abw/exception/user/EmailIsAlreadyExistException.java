package com.example.abw.exception.user;


public class EmailIsAlreadyExistException extends Exception {
    public EmailIsAlreadyExistException(String message) {
        super(message);
    }
}
