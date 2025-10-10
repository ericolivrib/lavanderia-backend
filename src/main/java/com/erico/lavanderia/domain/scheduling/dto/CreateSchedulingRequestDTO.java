package com.erico.lavanderia.domain.scheduling.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateSchedulingRequestDTO(UUID userId, LocalDateTime dateTime) {
}
