package com.erico.lavanderia.application.dto;

import java.util.List;

public record UserSchedulesResponseBody(
        String message,
        List<SchedulingResponseDTO> data
) implements ApiResponseBody<List<SchedulingResponseDTO>> {
}
