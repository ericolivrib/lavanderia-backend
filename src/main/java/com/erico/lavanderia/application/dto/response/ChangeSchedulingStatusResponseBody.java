package com.erico.lavanderia.application.dto.response;

import com.erico.lavanderia.application.dto.SchedulingStatusChangeResponseDTO;

public record ChangeSchedulingStatusResponseBody(
        String message,
        SchedulingStatusChangeResponseDTO data
) implements ApiResponseBody<SchedulingStatusChangeResponseDTO> {
}
