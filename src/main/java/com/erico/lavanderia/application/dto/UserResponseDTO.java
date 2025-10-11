package com.erico.lavanderia.application.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String registration,
        Integer apartment
) {
}
