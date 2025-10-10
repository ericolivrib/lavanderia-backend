package com.erico.lavanderia.domain.scheduling;

public enum SchedulingStatus {

    REGISTERED("Agendamento criado"),
    IN_PROGRESS("As roupas foram colocadas na lavadoura"),
    CANCELED("Agendamento cancelado"),
    FINISHED("As roupas terminaram de lavar"),
    INTERRUPTED("Lavagem interrompida por motivos de força maior (ex.: lavadoura com defeito)");

    private final String description;

    SchedulingStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
