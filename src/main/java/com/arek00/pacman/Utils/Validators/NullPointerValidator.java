package com.arek00.pacman.Utils.Validators;

/**
 * Examine arguments for being null.
 */
public class NullPointerValidator {
    public static void validate(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("Given argument is null " + object.toString());
        }
    }
}
