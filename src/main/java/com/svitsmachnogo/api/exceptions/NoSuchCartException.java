package com.svitsmachnogo.api.exceptions;

public class NoSuchCartException extends RuntimeException{
    public NoSuchCartException(String message) {
        super(message);
    }
}
