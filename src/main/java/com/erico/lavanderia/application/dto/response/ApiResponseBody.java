package com.erico.lavanderia.application.dto.response;

public interface ApiResponseBody<T> {
    String message();
    T data();
}
