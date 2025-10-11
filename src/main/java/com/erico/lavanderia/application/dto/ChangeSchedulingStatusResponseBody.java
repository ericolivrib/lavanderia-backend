package com.erico.lavanderia.application.dto;

public record ChangeSchedulingStatusResponseBody(
        String message,
        ChangeSchedulingStatusResponseDTO data
) implements ApiResponseBody<ChangeSchedulingStatusResponseDTO> {
}
