package com.erico.lavanderia.domain.scheduling.dto;

import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateSchedulingResponseDTO(UUID id, UUID userRegistration, LocalDate date, LocalTime time, SchedulingStatus status) {
}
