package com.erico.lavanderia.domain.user;

import com.erico.lavanderia.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public final class Email extends ValueObject<String> {

    private static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    @Column(name = "email", nullable = false, unique = true)
    private String value;

    protected Email() {
        super(null);
    }

    public Email(String email) {
        if (!PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        super(email);
        this.value = email;
    }

    @Override
    public String getValue() {
        return value;
    }
}
