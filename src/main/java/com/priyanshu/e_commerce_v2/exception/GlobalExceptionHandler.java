package com.priyanshu.e_commerce_v2.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.priyanshu.e_commerce_v2.dto.exception.ErrorResponse;
import com.priyanshu.e_commerce_v2.util.mappers.ErrorResponseMappers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseMappers responseMappers;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ResponseEntity<ErrorResponse> handleAccountDisabledException(AccountDisabledException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicateCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCredentialsException(DuplicateCredentialsException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientQuantityException(InsufficientQuantityException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedAccessException(UnAuthorizedAccessException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred", request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errors);
        ErrorResponse response = responseMappers.toErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed: " + errorMessage,
                request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
