package com.erico.lavanderia.application.mapper;

import com.erico.lavanderia.application.dto.ChangeSchedulingDateTimeResponseDTO;
import com.erico.lavanderia.application.dto.UserSchedulingResponseDTO;
import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.application.dto.CreateSchedulingResponseDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SchedulingMapper {

    public <T> T mapToDataTransferObject(Scheduling scheduling, Class<T> targetClass) {
        Map<Class<?>, Object> classMap = Map.of(
                CreateSchedulingResponseDTO.class, new CreateSchedulingResponseDTO(scheduling),
                UserSchedulingResponseDTO.class, new UserSchedulingResponseDTO(scheduling),
                ChangeSchedulingDateTimeResponseDTO.class, new ChangeSchedulingDateTimeResponseDTO(scheduling)
        );

        if (classMap.containsKey(targetClass)) {
            var dto = classMap.get(targetClass);
            return targetClass.cast(dto);
        }

        throw new RuntimeException("A classe informada não pode ser mapeada para uma entidade Scheduling");
    }


}
