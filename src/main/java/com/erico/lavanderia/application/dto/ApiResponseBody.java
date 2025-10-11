package com.erico.lavanderia.application.dto;

public interface ApiResponseBody<T> {
    String message();
    T data();
}
