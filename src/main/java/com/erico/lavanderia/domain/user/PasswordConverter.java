package com.erico.lavanderia.domain.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        return password.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String s) {
        return new Password(s);
    }
}
