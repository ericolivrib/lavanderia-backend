package com.erico.lavanderia.application.dto.response;

import com.erico.lavanderia.application.dto.SchedulingResponseDTO;
import org.springframework.data.domain.Page;

public record SchedulingPageResponseBody(
        String message,
        Page<SchedulingResponseDTO> data
) implements ApiResponseBody<Page<SchedulingResponseDTO>> {
}
