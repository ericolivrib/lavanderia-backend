package com.erico.lavanderia.http.controller;

import com.erico.lavanderia.application.dto.ApiResponseBody;
import com.erico.lavanderia.application.service.SchedulingService;
import com.erico.lavanderia.application.dto.CreateSchedulingRequestDTO;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import com.erico.lavanderia.http.docs.CreateSchedulingApiDoc;
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

}
