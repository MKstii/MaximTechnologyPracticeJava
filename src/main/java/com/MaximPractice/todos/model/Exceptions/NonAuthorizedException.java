package com.MaximPractice.todos.model.Exceptions;

public class NonAuthorizedException extends RuntimeException {
    public NonAuthorizedException(String msg) {
        super(msg);
    }
}
