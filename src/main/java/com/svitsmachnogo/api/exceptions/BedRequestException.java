package com.svitsmachnogo.api.exceptions;

public class BedRequestException extends RuntimeException{
    public BedRequestException() {
        super();
    }

    public BedRequestException(String message) {
        super(message);
    }

    public BedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BedRequestException(Throwable cause) {
        super(cause);
    }
}
