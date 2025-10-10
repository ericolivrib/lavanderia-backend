package com.erico.lavanderia.http.handler;

import com.erico.lavanderia.application.dto.ApiErrorResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ResponseStatusExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponseBody> handleResponseStatusException(ResponseStatusException e) {
        int statusCode = e.getStatusCode().value();
        var errorResponseBody = new ApiErrorResponseBody(e.getReason());

        return ResponseEntity.status(statusCode)
                .body(errorResponseBody);
    }

}
