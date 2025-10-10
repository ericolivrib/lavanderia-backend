package com.erico.lavanderia.http.controller;

import com.erico.lavanderia.domain.scheduling.dto.CreateSchedulingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulingController {

    @PostMapping("/v1/schedules")
    public ResponseEntity<CreateSchedulingResponseDTO> createScheduling(@RequestBody CreateSchedulingResponseDTO requestBody) {
        // TODO
        return ResponseEntity.ok().build();
    }


}
