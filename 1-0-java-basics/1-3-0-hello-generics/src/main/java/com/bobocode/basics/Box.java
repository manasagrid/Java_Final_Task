package com.bobocode.basics;


/**
 * {@link Box} is a container class that can store a value of any given type.
 * It uses generics to enforce type safety at compile time, avoiding the need for runtime casting.
 * @param <T> the type of value that this box can store
 */
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
