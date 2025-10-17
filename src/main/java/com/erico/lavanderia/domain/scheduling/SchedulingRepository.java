package com.erico.lavanderia.domain.scheduling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID>, JpaSpecificationExecutor<Scheduling> {

    boolean existsByDateTimeAndUserId(SchedulingDateTime dateTime, UUID userId);

    @Query("SELECT s FROM Scheduling s LEFT JOIN User u ON s.user.id = u.id WHERE u.id = :userId")
    List<Scheduling> findByUserId(@Param("userId") UUID userId);
}
