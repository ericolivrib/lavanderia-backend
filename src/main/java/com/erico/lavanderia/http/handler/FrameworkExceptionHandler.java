package com.erico.lavanderia.http.handler;

import com.erico.lavanderia.application.dto.response.ApiErrorResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FrameworkExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponseBody> handleHttpMessageNotReadableException(Exception e) {
        var errorResponseBody = new ApiErrorResponseBody("Dados de requisição inválidos");
        return ResponseEntity.badRequest().body(errorResponseBody);
    }

}
