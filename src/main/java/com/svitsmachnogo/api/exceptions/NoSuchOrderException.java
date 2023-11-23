package com.svitsmachnogo.api.exceptions;

import java.util.function.Supplier;

public class NoSuchOrderException extends Exception {

    public NoSuchOrderException(String message) {
        super(message);
    }
}
