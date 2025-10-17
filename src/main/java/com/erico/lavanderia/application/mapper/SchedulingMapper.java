package com.erico.lavanderia.application.mapper;

import com.erico.lavanderia.application.dto.SchedulingDateTimeChangeResponseDTO;
import com.erico.lavanderia.application.dto.SchedulingResponseDTO;
import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.application.dto.SchedulingCreateResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SchedulingMapper {

    public <T> T mapToDataTransferObject(Scheduling scheduling, Class<T> targetClass) {
        Map<Class<?>, Object> classMap = Map.of(
                SchedulingCreateResponseDTO.class, new SchedulingCreateResponseDTO(scheduling),
                SchedulingResponseDTO.class, new SchedulingResponseDTO(scheduling),
                SchedulingDateTimeChangeResponseDTO.class, new SchedulingDateTimeChangeResponseDTO(scheduling)
        );

        if (classMap.containsKey(targetClass)) {
            var dto = classMap.get(targetClass);
            return targetClass.cast(dto);
        }

        throw new RuntimeException("A classe informada não pode ser mapeada para uma entidade Scheduling");
    }


}
