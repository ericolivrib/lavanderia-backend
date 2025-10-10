package com.erico.lavanderia.domain;

public abstract class ValueObject<T> {

    protected T value;

    protected ValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
