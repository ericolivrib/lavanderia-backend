package com.erico.lavanderia.application.dto;

public record ChangeSchedulingDateTimeResponseBody(
        String message,
        ChangeSchedulingDateTimeResponseDTO data
) implements ApiResponseBody<ChangeSchedulingDateTimeResponseDTO> {
}
