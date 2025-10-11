package com.erico.lavanderia.http.controller;

import com.erico.lavanderia.application.dto.*;
import com.erico.lavanderia.application.service.SchedulingService;
import com.erico.lavanderia.http.docs.CreateSchedulingApiDoc;
import com.erico.lavanderia.http.docs.GetUserSchedulesApiDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @CreateSchedulingApiDoc
    @PostMapping("/v1/users/{userId}/schedules")
    public ResponseEntity<ApiResponseBody<CreateSchedulingResponseDTO>> createScheduling(@PathVariable UUID userId, @RequestBody CreateSchedulingRequestDTO requestBody) {
        CreateSchedulingResponseDTO createdScheduling = schedulingService.createScheduling(userId, requestBody.dateTime());

        var responseBody = new ApiResponseBody<>("Agendamento criado com sucesso", createdScheduling);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @GetUserSchedulesApiDoc
    @GetMapping("/v1/users/{userId}/schedules")
    public ResponseEntity<ApiResponseBody<List<UserSchedulingResponseDTO>>> getUserSchedules(@PathVariable UUID userId) {
        List<UserSchedulingResponseDTO> userSchedules = schedulingService.getUserSchedules(userId);

        String message = userSchedules.isEmpty() ? "Usuário não possui agendamentos" : "Agendamentos do usuário recuperados com sucesso";
        var responseBody = new ApiResponseBody<>(message, userSchedules);

        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/v1/schedules")
    public ResponseEntity<ApiResponseBody<ChangeSchedulingDateTimeResponseDTO>> changeSchedulingDateTime(@PathVariable UUID schedulingId, @RequestBody ChangeSchedulingDateTimeRequestDTO requestBody) {
        ChangeSchedulingDateTimeResponseDTO updatedScheduling = null;

        var responseBody = new ApiResponseBody<>("Horário do agendamento alterado com sucesso", updatedScheduling);
        return ResponseEntity.ok(responseBody);
    }
}
