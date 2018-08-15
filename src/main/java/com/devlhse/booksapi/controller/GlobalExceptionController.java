package com.devlhse.booksapi.controller;

import com.devlhse.booksapi.dto.ErrorMessage;
import com.devlhse.booksapi.exception.EntityNotFoundException;
import com.devlhse.booksapi.exception.FieldEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldEmptyException.class)
    public ResponseEntity<ErrorMessage> handleFieldEmptyException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
