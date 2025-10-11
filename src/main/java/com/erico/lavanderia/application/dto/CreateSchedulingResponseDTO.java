package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateSchedulingResponseDTO(UUID id, UUID userId, LocalDateTime dateTime, SchedulingStatus status) {}
