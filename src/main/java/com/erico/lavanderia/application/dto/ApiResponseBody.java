package com.erico.lavanderia.application.dto;

public record ApiResponseBody<T>(String message, T data) {
}
