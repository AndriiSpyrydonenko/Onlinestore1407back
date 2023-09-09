package com.svitsmachnogo.api.exceptions;

public class WrongPriceFilterException extends Exception{

    public WrongPriceFilterException(String message) {
        super(message);
    }

    public WrongPriceFilterException() {

    }
}
