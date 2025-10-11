package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.Scheduling;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangeSchedulingDateTimeResponseDTO(
        UUID id,
        LocalDateTime dateTime
) {

    public ChangeSchedulingDateTimeResponseDTO(Scheduling scheduling) {
        this(scheduling.getId(), scheduling.getDateTime());
    }
}
