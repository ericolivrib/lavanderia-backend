package com.erico.lavanderia.domain.user;

import com.erico.lavanderia.domain.scheduling.Scheduling;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity
public class User {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;

    private String registration;

    private Integer apartment;

    @OneToMany(mappedBy = "user")
    private List<Scheduling> schedules;

    protected User() {
    }

    public User(String name, Email email, Password password, String registration, Integer apartment) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.apartment = apartment;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getRegistration() {
        return registration;
    }

    public Integer getApartment() {
        return apartment;
    }

    public List<Scheduling> getSchedules() {
        return schedules;
    }
}
