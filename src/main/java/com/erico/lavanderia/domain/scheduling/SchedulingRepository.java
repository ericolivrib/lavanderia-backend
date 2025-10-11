package com.erico.lavanderia.domain.scheduling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

    boolean existsByDateTimeAndUserId(SchedulingDateTime dateTime, UUID userId);

    List<Scheduling> findByUserId(UUID userId);
}
