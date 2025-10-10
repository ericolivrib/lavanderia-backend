package com.erico.lavanderia.http.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(tags = "schedules", summary = "Novo agendamento", description = "Cria um novo agendamento para o usuário", responses = {
        @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "409", description = "Agendamento com data e hora já existentes"),
        @ApiResponse(responseCode = "422", description = "Inserção de data/hora no passado")
})
public @interface CreateSchedulingApiDoc {
}

