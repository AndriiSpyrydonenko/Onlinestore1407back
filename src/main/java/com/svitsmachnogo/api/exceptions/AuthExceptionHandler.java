package com.svitsmachnogo.api.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for authentication-related exceptions.
 * This controller advice class handles various exceptions and maps them to appropriate
 * HTTP status codes along with custom error responses in the form of {@link AppError}.
 *
 * @author Vanya Demydenko
 * @see AppError
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    /**
     * Handles BadCredentialsException.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleIncorrectCredential(BadCredentialsException exception) {
        AppError error =AppError.of(401, "Incorrect login or password!");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles UserAlreadyExistException.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleUserAlreadyExist(UserAlreadyExistException exception) {
        AppError error = AppError.of(409, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles DifferentPasswordsExceptions.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleDifferentPasswords(DifferentPasswordsExceptions exception) {
        AppError error = AppError.of(400, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles FileExtensionException.
     */
    @ExceptionHandler(value = FileExtensionException.class)
    public ResponseEntity<AppError> handleFileFormatException(FileExtensionException exception) {
        AppError error = AppError.of(415, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handles BedRequestException.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleBedRequestException(BedRequestException exception) {
        AppError error = AppError.of(400, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ExpiredJwtException.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleExpiredJwt(ExpiredJwtException exception) {
        AppError error = AppError.of(401, "Confirmation link expired!!");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles other unexpected exceptions.
     */
    @ExceptionHandler
    public ResponseEntity<AppError> handleGenericException(Exception exception) {
        AppError error = AppError.of(500, "Internal Server Error. " + exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

