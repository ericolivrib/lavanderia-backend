package com.erico.lavanderia.application.dto.response;

import com.erico.lavanderia.application.dto.SchedulingDateTimeChangeResponseDTO;

public record ChangeSchedulingDateTimeResponseBody(
        String message,
        SchedulingDateTimeChangeResponseDTO data
) implements ApiResponseBody<SchedulingDateTimeChangeResponseDTO> {
}
