package com.erico.lavanderia.application.dto;

import java.util.List;

public record UserSchedulesResponseBody(
        String message,
        List<UserSchedulingResponseDTO> data
) implements ApiResponseBody<List<UserSchedulingResponseDTO>> {
}
