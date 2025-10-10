package com.erico.lavanderia.domain.scheduling;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;

@Converter
public class SchedulingDateTimeConverter implements AttributeConverter<SchedulingDateTime, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(SchedulingDateTime schedulingDateTime) {
        return schedulingDateTime.getValue();
    }

    @Override
    public SchedulingDateTime convertToEntityAttribute(LocalDateTime dateTime) {
        return new SchedulingDateTime(dateTime);
    }
}
