package com.erico.lavanderia.http.docs;

import com.erico.lavanderia.application.dto.response.ApiErrorResponseBody;
import com.erico.lavanderia.application.dto.response.ChangeSchedulingDateTimeResponseBody;
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
@Operation(tags = "schedules", summary = "Alteração de horário", description = "Altera a data e hora de um agendamento", responses = {
        @ApiResponse(responseCode = "200", description = "Horário do agendamento alterado com sucesso", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ChangeSchedulingDateTimeResponseBody.class, description = "Dados do agendamento alterado"))
        }),
        @ApiResponse(responseCode = "400", description = "Formado de Data e Hora ou do UUID do agendamento estão inválidos", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "409", description = "Agendamento com mesma data e hora já registrado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "422", description = "Inserção de data/hora no passado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        })
})
public @interface ChangeSchedulingDateTimeApiDoc {
}
