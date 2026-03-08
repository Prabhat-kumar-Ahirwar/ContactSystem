package com.example.ContactSystem.ExceptionHandler;

public class DuplicateContactException extends RuntimeException{
    public DuplicateContactException(String message) {
        super(message);
    }
}
