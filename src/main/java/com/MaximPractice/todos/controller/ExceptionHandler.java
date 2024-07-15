package com.MaximPractice.todos.controller;

import com.MaximPractice.todos.model.Exceptions.NonAuthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    public ResponseEntity<String> handleNonAuthorizedException(NonAuthorizedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Не достаточно прав");
    }
}
