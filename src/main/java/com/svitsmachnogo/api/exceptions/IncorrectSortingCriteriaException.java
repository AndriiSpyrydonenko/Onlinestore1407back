package com.svitsmachnogo.api.exceptions;

public class IncorrectSortingCriteriaException extends Exception {
    public IncorrectSortingCriteriaException(String message) {
        super(message);
    }

    public IncorrectSortingCriteriaException(String message, Throwable cause) {
        super(message, cause);
    }
}
