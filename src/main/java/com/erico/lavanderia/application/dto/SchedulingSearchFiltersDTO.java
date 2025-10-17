package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.OffsetDateTime;

public record SchedulingSearchFiltersDTO(
        String registration,
        SchedulingStatus status,
        OffsetDateTime initialDateTime,
        OffsetDateTime endDateTime
) {
}
