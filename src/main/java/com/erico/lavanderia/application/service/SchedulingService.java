package com.erico.lavanderia.application.service;

import com.erico.lavanderia.application.dto.ChangeSchedulingDateTimeResponseDTO;
import com.erico.lavanderia.application.dto.ChangeSchedulingStatusResponseDTO;
import com.erico.lavanderia.application.dto.UserSchedulingResponseDTO;
import com.erico.lavanderia.application.mapper.SchedulingMapper;
import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.domain.scheduling.SchedulingDateTime;
import com.erico.lavanderia.domain.scheduling.SchedulingRepository;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import com.erico.lavanderia.domain.scheduling.SchedulingStatus;
import com.erico.lavanderia.domain.user.User;
import com.erico.lavanderia.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulingService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulingService.class);

    private final SchedulingRepository schedulingRepository;
    private final UserRepository userRepository;

    public SchedulingService(SchedulingRepository schedulingRepository, UserRepository userRepository) {
        this.schedulingRepository = schedulingRepository;
        this.userRepository = userRepository;
    }

    public CreateSchedulingResponseDTO createScheduling(UUID userId, LocalDateTime dateTime) {
        Optional<User> optionalUser = userRepository.findById(userId);

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

        return new CreateSchedulingResponseDTO(scheduling);
    }

    public List<UserSchedulingResponseDTO> getUserSchedules(UUID userId) {
        boolean userExists = userRepository.existsById(userId);

        if (!userExists) {
            LOG.info("Tentativa de recuperação de agendamentos de usuário inexistente (userId={})", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para busca de agendamentos");
        }

        List<Scheduling> userSchedules = schedulingRepository.findByUserId(userId);

        return userSchedules.stream()
                .map(UserSchedulingResponseDTO::new)
                .toList();
    }

    public ChangeSchedulingDateTimeResponseDTO changeSchedulingDateTime(UUID schedulingId, LocalDateTime dateTime) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(schedulingId);

        if (optionalScheduling.isEmpty()) {
            LOG.info("Tentativa de alteração de horário para agendamento inexistente (schedulingId={})", schedulingId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado para alteração de horário");
        }

        Scheduling scheduling =  optionalScheduling.get();

        if (dateTime.equals(scheduling.getDateTime())) {
            LOG.info("Tentativa de alteração de horário para o mesmo horário (schedulingId={}; dateTime={}", schedulingId, dateTime);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O agendamento informado já está registrado com este mesmo horário");
        }

        try {
            scheduling.changeDateTime(dateTime);
        } catch (IllegalArgumentException e) {
            LOG.info("Tentativa de alteração de horário de agendamento para date e hora no passado (dateTime={})", dateTime);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }

        schedulingRepository.save(scheduling);
        return new ChangeSchedulingDateTimeResponseDTO(scheduling);
    }

    public ChangeSchedulingStatusResponseDTO cancelScheduling(UUID schedulingId) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(schedulingId);

        if (optionalScheduling.isEmpty()) {
            LOG.info("Tentativa de cancelamento de agendamento inexistente (schedulingId={})", schedulingId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado para o cancelamento");
        }

        Scheduling scheduling =  optionalScheduling.get();

        if (scheduling.getStatus() == SchedulingStatus.CANCELED) {
            LOG.info("Tentativa de agendamento de status já cancelado");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O agendamento informado já está cancelado");
        }

        scheduling.cancel();

        schedulingRepository.save(scheduling);

        return new ChangeSchedulingStatusResponseDTO(scheduling);
    }
}
