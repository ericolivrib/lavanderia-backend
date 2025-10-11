package com.erico.lavanderia.application.mapper;

import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class SchedulingMapper {

    public <T> T mapToDataTransferObject(Scheduling scheduling, Class<T> targetClass) {
        if (targetClass.equals(CreateSchedulingResponseDTO.class)) {
            var dto = new CreateSchedulingResponseDTO(scheduling.getId(), scheduling.getUser().getId(), scheduling.getDateTime(), scheduling.getStatus());
            return targetClass.cast(dto);
        }

        throw new RuntimeException("A classe informada não pode ser mapeada para uma entidade Scheduling");
    }
}
