package com.erico.lavanderia.application.dto;


import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserSchedulingResponseDTO(
        UUID id,
        UserResponseDTO user,
        LocalDateTime dateTime,
        SchedulingStatus status
) {
}
