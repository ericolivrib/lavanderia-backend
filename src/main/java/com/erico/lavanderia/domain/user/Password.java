package com.erico.lavanderia.domain.user;

import com.erico.lavanderia.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public final class Password extends ValueObject<String> {

    @Column(name = "password")
    private String value;

    private static final Pattern PATTERN = Pattern.compile("^\\$2[aby]\\$\\d{2}\\$[./A-Za-z0-9]{53}$");

    public Password(String password) {
        super(password);
        this.value = password;
    }

    protected Password() {
        super(null);
    }

    public void encode() {
        if (!isEncoded()) {
            // TODO
        }
    }

    public boolean compare(String raw) {
        if (!isEncoded()) {
            return false;
        }

        return true;
    }

    public boolean isEncoded() {
        return PATTERN.matcher(value).matches();
    }

    @Override
    public String getValue() {
        return value;
    }
}
