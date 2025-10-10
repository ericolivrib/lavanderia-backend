package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateSchedulingResponseDTO(UUID id, String userRegistration, LocalDate date, LocalTime time, SchedulingStatus status) {
}
