package com.bobocode.fp;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers {
    private PrimeNumbers() {
    }

    /**
     * Generates an infinite int stream of prime numbers.
     * The stream values are 2, 3, 5,... and so on.
     *
     * @return an infinite int stream of prime numbers
     */
    public static IntStream stream() {
        return IntStream.iterate(2, PrimeNumbers::nextPrime);
    }

    /**
     * Generates an int stream of a certain amount of prime numbers.
     * It is based on the {@link PrimeNumbers#stream()} but specifies the exact size of the stream.
     *
     * @param size the amount of prime numbers to generate
     * @return an int stream of prime numbers with a specified size
     */
    public static IntStream stream(int size) {
        return stream().limit(size);
    }

    /**
     * Calculates the sum of the first n prime numbers.
     * E.g. if n = 5, the result should be 2 + 3 + 5 + 7 + 11 = 28
     *
     * @param n the number of first prime numbers
     * @return the sum of n prime numbers
     */
    public static int sum(int n) {
        return stream(n).sum();
    }

    /**
     * Collects the first n prime numbers.
     *
     * @param n the number of prime numbers to collect
     * @return a list of collected prime numbers
     */
    public static List<Integer> list(int n) {
        return stream(n).boxed().collect(Collectors.toList());
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing the found prime number
     *
     * @param idx      the position of a prime number (index), starting from 0
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        stream().skip(idx).findFirst().ifPresent(consumer);
    }

    /**
     * Creates a list of n prime numbers and returns a map where all of those prime numbers are grouped.
     * The key represents an amount of digits and the value is a corresponding list of all prime numbers.
     *
     * @param n the amount of prime numbers
     * @return a map with prime numbers grouped by the amount of digits
     */
    public static Map<Integer, List<Integer>> groupByAmountOfDigits(int n) {
        return list(n).stream()
                .collect(Collectors.groupingBy(String::length));
    }

    // Helper method to find the next prime number
    private static int nextPrime(int number) {
        int candidate = number + 1;
        while (!isPrime(candidate)) {
            candidate++;
        }
        return candidate;
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
