package com.erico.lavanderia.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangeSchedulingDateTimeResponseDTO(
        UUID id,
        LocalDateTime dateTime
) {
}
