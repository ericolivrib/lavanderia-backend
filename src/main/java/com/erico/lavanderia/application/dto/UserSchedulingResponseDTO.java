package com.erico.lavanderia.application.dto;


import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserSchedulingResponseDTO(
        UUID id,
        UserResponseDTO user,
        LocalDateTime dateTime,
        SchedulingStatus status
) {

    public UserSchedulingResponseDTO(Scheduling scheduling) {
        this(scheduling.getId(), new UserResponseDTO(scheduling.getUser()), scheduling.getDateTime(), scheduling.getStatus());
    }
}
