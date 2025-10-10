package com.erico.lavanderia.domain.scheduling;

import com.erico.lavanderia.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
public class Scheduling {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "date_time", nullable = false))
    private SchedulingDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus status;

    protected Scheduling() {
    }

    public Scheduling(User user, SchedulingDateTime dateTime) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.dateTime = dateTime;
        this.status = SchedulingStatus.REGISTERED;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime.getValue();
    }

    public SchedulingStatus getStatus() {
        return status;
    }

    public void changeDateTime(LocalDateTime dateTime) {
        this.dateTime = new SchedulingDateTime(dateTime);
    }

    public void startWashing() {
        this.status = SchedulingStatus.IN_PROGRESS;
    }

    public void cancel() {
        this.status = SchedulingStatus.CANCELED;
    }

    public void finish() {
        this.status = SchedulingStatus.FINISHED;
    }

    public void interrupt() {
        this.status = SchedulingStatus.INTERRUPTED;
    }
}
