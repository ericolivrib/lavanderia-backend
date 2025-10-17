package com.erico.lavanderia.http.controller;

import com.erico.lavanderia.application.dto.*;
import com.erico.lavanderia.application.dto.request.ChangeSchedulingDateTimeRequestDTO;
import com.erico.lavanderia.application.dto.request.CreateSchedulingRequestDTO;
import com.erico.lavanderia.application.service.SchedulingService;
import com.erico.lavanderia.http.docs.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<CreateSchedulingResponseBody> createScheduling(@PathVariable UUID userId, @RequestBody CreateSchedulingRequestDTO requestBody) {
        CreateSchedulingResponseDTO createdScheduling = schedulingService.createScheduling(userId, requestBody.dateTime());

        var responseBody = new CreateSchedulingResponseBody("Agendamento criado com sucesso", createdScheduling);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBody);
    }


    @GetMapping("/schedules")
    public ResponseEntity<SchedulingPageResponseBody> getFilteredSchedules(@ModelAttribute SchedulingSearchFiltersDTO filters, @ParameterObject Pageable pageable) {
        Page<SchedulingResponseDTO> schedules = schedulingService.findSchedulesByFilters(filters, pageable);

        String message = schedules.isEmpty() ? "Não há agendamentos cadastrados" : "Agendamentos recuperados com sucesso";

        var responseBody = new SchedulingPageResponseBody(message, schedules);

        return ResponseEntity.ok(responseBody);
    }

    @GetUserSchedulesApiDoc
    @GetMapping("/v1/users/{userId}/schedules")
    public ResponseEntity<UserSchedulesResponseBody> getUserSchedules(@PathVariable UUID userId) {
        List<SchedulingResponseDTO> userSchedules = schedulingService.getUserSchedules(userId);

        String message = userSchedules.isEmpty() ? "Usuário não possui agendamentos" : "Agendamentos do usuário recuperados com sucesso";
        var responseBody = new UserSchedulesResponseBody(message, userSchedules);

        return ResponseEntity.ok(responseBody);
    }

    @ChangeSchedulingDateTimeApiDoc
    @PatchMapping("/v1/schedules/{id}")
    public ResponseEntity<ChangeSchedulingDateTimeResponseBody> changeSchedulingDateTime(@PathVariable("id") UUID schedulingId, @RequestBody ChangeSchedulingDateTimeRequestDTO requestBody) {
        ChangeSchedulingDateTimeResponseDTO updatedScheduling = schedulingService.changeSchedulingDateTime(schedulingId, requestBody.dateTime());

        var responseBody = new ChangeSchedulingDateTimeResponseBody("Horário do agendamento alterado com sucesso", updatedScheduling);

        return ResponseEntity.ok(responseBody);
    }

    @CancelSchedulingApiDoc
    @PutMapping("/v1/schedules/{id}/canceled")
    public ResponseEntity<ChangeSchedulingStatusResponseBody> cancelScheduling(@PathVariable("id") UUID schedulingId) {
        ChangeSchedulingStatusResponseDTO canceledScheduling = schedulingService.cancelScheduling(schedulingId);

        var responseBody = new ChangeSchedulingStatusResponseBody("Agendamento cancelado com sucesso", canceledScheduling);
        return ResponseEntity.ok(responseBody);
    }

    @StartWashingApiDoc
    @PutMapping("/v1/schedules/{id}/washing")
    public ResponseEntity<ChangeSchedulingStatusResponseBody> startWashing(@PathVariable("id") UUID schedulingId) {
        ChangeSchedulingStatusResponseDTO startedWashing = schedulingService.startWashing(schedulingId);

        var responseBody = new ChangeSchedulingStatusResponseBody("Lavagem de roupas iniciada com sucesso", startedWashing);
        return ResponseEntity.ok(responseBody);
    }

    @FinishSchedulingApiDoc
    @PutMapping("/v1/schedules/{id}/finished")
    public ResponseEntity<ChangeSchedulingStatusResponseBody> finishScheduling(@PathVariable("id") UUID schedulingId) {
        ChangeSchedulingStatusResponseDTO finishedScheduling = schedulingService.finishScheduling(schedulingId);

        var responseBody = new ChangeSchedulingStatusResponseBody("Agendamento finalizado com sucesso", finishedScheduling);
        return ResponseEntity.ok(responseBody);
    }

    @InterruptWashingApiDoc
    @PutMapping("/v1/schedules/{id}/interrupted")
    public ResponseEntity<ChangeSchedulingStatusResponseBody> interruptWashing(@PathVariable("id") UUID schedulingId) {
        ChangeSchedulingStatusResponseDTO interruptedScheduling = schedulingService.interruptWashing(schedulingId);

        var responseBody = new ChangeSchedulingStatusResponseBody("Lavagem interrompida com sucesso", interruptedScheduling);
        return ResponseEntity.ok(responseBody);
    }
}
