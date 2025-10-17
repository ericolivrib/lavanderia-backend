package com.erico.lavanderia.application.dto.response;

import com.erico.lavanderia.application.dto.SchedulingResponseDTO;

import java.util.List;

public record UserSchedulesResponseBody(
        String message,
        List<SchedulingResponseDTO> data
) implements ApiResponseBody<List<SchedulingResponseDTO>> {
}
