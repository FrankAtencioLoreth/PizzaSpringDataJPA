package com.platzi_pizzeria.platzi_pizzeria.service.exception;

public class EmailAPiException extends RuntimeException{

    public EmailAPiException() {
        super("Error sending email...");
    }
}
