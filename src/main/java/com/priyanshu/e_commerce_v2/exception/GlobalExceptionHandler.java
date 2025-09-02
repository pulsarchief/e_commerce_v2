package com.priyanshu.e_commerce_v2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ResponseEntity<ErrorResponse> handleAccountDisabledException(AccountDisabledException exception,
            HttpServletRequest request) {

        ErrorResponse response = responseMappers.toErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

}
