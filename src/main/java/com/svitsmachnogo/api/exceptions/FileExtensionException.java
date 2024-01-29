package com.svitsmachnogo.api.exceptions;

public class FileExtensionException extends RuntimeException{

    public FileExtensionException() {
    }

    public FileExtensionException(String message) {
        super(message);
    }

    public FileExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileExtensionException(Throwable cause) {
        super(cause);
    }
}
