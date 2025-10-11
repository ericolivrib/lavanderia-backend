package com.erico.lavanderia.application.dto;

import com.erico.lavanderia.domain.user.User;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String registration,
        Integer apartment
) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRegistration(), user.getApartment());
    }
}
