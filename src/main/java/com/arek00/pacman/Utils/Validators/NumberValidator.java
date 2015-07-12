package com.arek00.pacman.Utils.Validators;

/**
 * Validate numeric values
 */
public class NumberValidator {
    public static void checkNegativeNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Given argument is lower than zero.");
        }
    }

    public static void checkNegativeNumber(float number) {
        if (number < 0) {
            throw new IllegalArgumentException("Given argument is lower than zero.");
        }
    }

    public static void checkNumberIsZero(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Given argument equals zero.");
        }
    }

    public static void checkNumberIsZero(float number) {
        if (number == 0) {
            throw new IllegalArgumentException("Given argument equals zero.");
        }
    }
}
