package com.erico.lavanderia.application.service;

import com.erico.lavanderia.application.mapper.SchedulingMapper;
import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.domain.scheduling.SchedulingDateTime;
import com.erico.lavanderia.domain.scheduling.SchedulingRepository;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import com.erico.lavanderia.domain.user.User;
import com.erico.lavanderia.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SchedulingService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulingService.class);

    private final SchedulingRepository schedulingRepository;
    private final UserRepository userRepository;

    private final SchedulingMapper schedulingMapper;

    public SchedulingService(SchedulingRepository schedulingRepository, UserRepository userRepository, SchedulingMapper schedulingMapper) {
        this.schedulingRepository = schedulingRepository;
        this.userRepository = userRepository;
        this.schedulingMapper = schedulingMapper;
    }

    public CreateSchedulingResponseDTO createScheduling(UUID userId, LocalDateTime dateTime) {
        var optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            LOG.info("Tentativa de criação de agendamento para usuário inexistente (userId={})", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para realização do agendamento");
        }

        User user = optionalUser.get();

        SchedulingDateTime schedulingDateTime;

        try {
            schedulingDateTime = new SchedulingDateTime(dateTime);
        } catch (IllegalArgumentException e) {
            LOG.info("Tentativa de criação de agendamento com data/hora do passado (dateTime={})", dateTime);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }

        boolean hasInSameDateTime = schedulingRepository.existsByDateTimeAndUserId(schedulingDateTime, userId);

        if (hasInSameDateTime) {
            LOG.info("Tentativa de criação de agendamento em uma data/hora já existente para este usuário (dateTime={}; userId={})",
                    schedulingDateTime, userId);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um agendamento na mesma data e hora para este usuário");
        }

        var scheduling = new Scheduling(user, schedulingDateTime);

        schedulingRepository.save(scheduling);

        return schedulingMapper.mapToDataTransferObject(scheduling, CreateSchedulingResponseDTO.class);
    }

}
