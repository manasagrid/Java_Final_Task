package com.bobocode.fp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrazyLambdas {
    public static void main(String[] args) {
        // Example list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using a lambda expression to calculate the square of each number
        List<Integer> squaredNumbers = numbers.stream()
                .map(number -> square(number)) // Method reference
                .collect(Collectors.toList());

        // Output the results
        System.out.println("Squared Numbers: " + squaredNumbers);

        // Using the functional interface with a lambda expression
        SquareCalculator calculator = (number) -> number * number;
        int result = calculator.calculate(6);
        System.out.println("Square of 6 using functional interface: " + result);
    }

    // Method to calculate square of a number
    private static int square(int number) {
        return number * number;
    }
}
