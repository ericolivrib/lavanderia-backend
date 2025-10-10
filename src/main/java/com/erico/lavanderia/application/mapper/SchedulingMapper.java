package com.erico.lavanderia.application.mapper;

import com.erico.lavanderia.domain.scheduling.Scheduling;
import com.erico.lavanderia.application.dto.UserSchedulingResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class SchedulingMapper {

    public <T> T mapToDataTransferObject(Scheduling scheduling, Class<T> targetClass) {
        if (targetClass.equals(UserSchedulingResponseDTO.class)) {
            var dateTime = scheduling.getDateTime();

            var dto = new UserSchedulingResponseDTO(scheduling.getId(), scheduling.getUser().getRegistration(), dateTime.toLocalDate(), dateTime.toLocalTime(), scheduling.getStatus());
            return targetClass.cast(dto);
        }

        throw new RuntimeException("A classe informada não pode ser mapeada para uma entidade Scheduling");
    }
}
