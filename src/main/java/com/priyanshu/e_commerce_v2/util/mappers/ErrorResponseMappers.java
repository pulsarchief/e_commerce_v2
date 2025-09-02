package com.priyanshu.e_commerce_v2.util.mappers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.exception.ErrorResponse;

@Component
public class ErrorResponseMappers {

    public ErrorResponse toErrorResponse(HttpStatus status, String message, String path) {
        ErrorResponse response = new ErrorResponse();

        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        response.setPath(path);

        return response;
    }

}
