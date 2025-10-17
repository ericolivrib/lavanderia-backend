package com.erico.lavanderia.http.docs;

import com.erico.lavanderia.application.dto.response.ApiErrorResponseBody;
import com.erico.lavanderia.application.dto.response.ChangeSchedulingStatusResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@Operation(tags = "schedules", summary = "Cancelamento de agendamento", description = "Realiza o cancelamento de um agendamento a partir de seu ID", responses = {
        @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ChangeSchedulingStatusResponseBody.class, description = "Agendamento cancelado")))
        }),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "409", description = "O agendamento informado já está com status de cancelado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        })
})
public @interface CancelSchedulingApiDoc {
}
