package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.Scheduling;

import java.time.LocalDateTime;
import java.util.UUID;

public record SchedulingDateTimeChangeResponseDTO(
        UUID id,
        LocalDateTime dateTime
) {

    public SchedulingDateTimeChangeResponseDTO(Scheduling scheduling) {
        this(scheduling.getId(), scheduling.getDateTime());
    }
}
