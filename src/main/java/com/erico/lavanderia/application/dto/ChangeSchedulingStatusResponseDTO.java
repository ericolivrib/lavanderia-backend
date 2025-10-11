package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.util.UUID;

public record ChangeSchedulingStatusResponseDTO(
        UUID id,
        SchedulingStatus status
) {

    public ChangeSchedulingStatusResponseDTO(Scheduling scheduling) {
        this(scheduling.getId(), scheduling.getStatus());
    }
}
