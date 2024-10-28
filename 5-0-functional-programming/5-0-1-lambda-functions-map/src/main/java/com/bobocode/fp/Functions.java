package com.bobocode.fp;

/**
 * An util class that provides a factory method for creating an instance of a {@link FunctionMap} filled with a list of functions.
 *
 * @author Taras Boychuk
 */
public class Functions {
    private Functions() {
    }

    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        // Adding functions using lambda expressions
        intFunctionMap.addFunction("abs", (Integer x) -> x < 0 ? -x : x);
        intFunctionMap.addFunction("sgn", (Integer x) -> (x > 0) ? 1 : (x < 0) ? -1 : 0);
        intFunctionMap.addFunction("increment", (Integer x) -> x + 1);
        intFunctionMap.addFunction("decrement", (Integer x) -> x - 1);
        intFunctionMap.addFunction("square", (Integer x) -> x * x);

        return intFunctionMap;
    }
}
