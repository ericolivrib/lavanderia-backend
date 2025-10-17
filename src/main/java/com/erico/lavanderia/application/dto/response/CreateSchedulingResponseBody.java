package com.erico.lavanderia.application.dto.response;

import com.erico.lavanderia.application.dto.SchedulingCreateResponseDTO;

public record CreateSchedulingResponseBody(
        String message,
        SchedulingCreateResponseDTO data
) implements ApiResponseBody<SchedulingCreateResponseDTO> {
}
