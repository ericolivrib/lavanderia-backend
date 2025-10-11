package com.erico.lavanderia.http.docs;

import com.erico.lavanderia.application.dto.ApiErrorResponseBody;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import com.erico.lavanderia.application.dto.UserSchedulingResponseDTO;
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
@Operation(tags = "schedules", summary = "Agendamentos do usuário", description = "Busca os agendamentos a partir do ID do usuário", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de agendamentos do usuário recuperada com sucesso", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserSchedulingResponseDTO.class, description = "Agendamentos do usuário")))
        }),
        @ApiResponse(responseCode = "400", description = "Formado do UUID do usuário é inválido", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiErrorResponseBody.class))
        })
})
public @interface GetUserSchedulesApiDoc {
}
