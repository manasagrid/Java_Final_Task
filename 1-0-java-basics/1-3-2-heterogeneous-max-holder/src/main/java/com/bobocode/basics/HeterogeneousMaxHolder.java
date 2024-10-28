package com.bobocode.basics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HeterogeneousMaxHolder {
    private final Map<Class<?>, Object> maxValuesMap = new HashMap<>();

    /**
     * Stores a provided value by its type if it's greater than the current maximum.
     *
     * @param type  a provided value type
     * @param value a value to put
     * @param <T>   value type parameter
     * @return the smaller value between the provided value and the current maximum
     */
    public <T extends Comparable<T>> T put(Class<T> type, T value) {
        Objects.requireNonNull(type, "Type must not be null");
        Objects.requireNonNull(value, "Value must not be null");

        T currentMax = (T) maxValuesMap.get(type);

        if (currentMax == null || value.compareTo(currentMax) > 0) {
            maxValuesMap.put(type, value);
            return currentMax;
        }
        return value;
    }

    /**
     * Stores a provided value by its type using a custom comparator. If null is passed for comparison,
     * it will consider null values smaller than any non-null object.
     *
     * @param type       a provided value type
     * @param value      a value to put
     * @param comparator a custom comparator for the given type
     * @param <T>        value type parameter
     * @return the smaller value between the provided value and the current maximum
     */
    public <T> T put(Class<T> type, T value, Comparator<? super T> comparator) {
        Objects.requireNonNull(type, "Type must not be null");
        Objects.requireNonNull(value, "Value must not be null");
        Objects.requireNonNull(comparator, "Comparator must not be null");

        Comparator<T> nullSafeComparator = Comparator.nullsFirst(comparator);
        T currentMax = (T) maxValuesMap.get(type);

        if (currentMax == null || nullSafeComparator.compare(value, currentMax) > 0) {
            maxValuesMap.put(type, value);
            return currentMax;
        }
        return value;
    }

    /**
     * Retrieves the max value by the given type.
     *
     * @param type a provided value type
     * @param <T>  value type parameter
     * @return the current max value or null if none is stored
     */
    public <T> T getMax(Class<T> type) {
        Objects.requireNonNull(type, "Type must not be null");
        return (T) maxValuesMap.get(type);
    }
}
