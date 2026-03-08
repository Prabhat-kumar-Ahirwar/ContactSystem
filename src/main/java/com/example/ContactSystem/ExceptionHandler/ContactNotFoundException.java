package com.example.ContactSystem.ExceptionHandler;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String message){
        super(message);
    }

}
