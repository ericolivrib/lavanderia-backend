package com.erico.lavanderia.domain.scheduling;

import com.erico.lavanderia.domain.ValueObject;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public final class SchedulingDateTime extends ValueObject<LocalDateTime> {

    @Column(name = "date_time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime value;

    public SchedulingDateTime(LocalDateTime dateTime) {
        boolean isInThePast = dateTime.isBefore(LocalDateTime.now());

        if (isInThePast) {
            throw new IllegalArgumentException("A data de agendamento não pode estar no passado");
        }

        super(dateTime);
        this.value = dateTime;
    }

    protected SchedulingDateTime() {
        super(null);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
