package com.erico.lavanderia.application.dto;

public record CreateSchedulingResponseBody(
        String message,
        CreateSchedulingResponseDTO data
) implements ApiResponseBody<CreateSchedulingResponseDTO> {
}
