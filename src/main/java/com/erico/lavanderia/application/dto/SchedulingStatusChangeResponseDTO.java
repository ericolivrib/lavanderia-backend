package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.util.UUID;

public record SchedulingStatusChangeResponseDTO(
        UUID id,
        SchedulingStatus newStatus,
        SchedulingStatus oldStatus
        ) {

    public SchedulingStatusChangeResponseDTO(Scheduling scheduling, SchedulingStatus oldStatus) {
        this(scheduling.getId(), scheduling.getStatus(), oldStatus);
    }
}
