package com.erico.lavanderia.http.docs;

import com.erico.lavanderia.application.dto.ApiErrorResponseBody;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(tags = "schedules", summary = "Novo agendamento", description = "Cria um novo agendamento para o usuário", responses = {
        @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateSchedulingResponseDTO.class, description = "Agendamento recém criado"))
        }),
        @ApiResponse(responseCode = "400", description = "Formado de Data e Hora ou do UUID do usuário estão inválidos", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "409", description = "Agendamento com data e hora já existentes", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "422", description = "Inserção de data/hora no passado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        })
})
public @interface CreateSchedulingApiDoc {
}

