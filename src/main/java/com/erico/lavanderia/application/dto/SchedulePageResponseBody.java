package com.erico.lavanderia.application.dto;

import org.springframework.data.domain.Page;

public record SchedulePageResponseBody(
        String message,
        Page<SchedulingResponseDTO> data
) implements ApiResponseBody<Page<SchedulingResponseDTO>> {
}
