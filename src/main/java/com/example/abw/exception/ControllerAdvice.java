package com.example.abw.exception;

import com.example.abw.exception.user.EmailIsAlreadyExistException;
import com.example.abw.exception.user.UserOrPasswordException;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> pageableParamsHandler(PropertyReferenceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationErrorHandler(ValidationException e) {
        return new ResponseEntity<>(e.getFullMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIsAlreadyExistException.class)
    public ResponseEntity<?> emailExistHandler(EmailIsAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserOrPasswordException.class)
    public ResponseEntity<?> wrongUserOrPasswordHandler(UserOrPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
