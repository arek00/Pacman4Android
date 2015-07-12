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

    public static void validate(Object[] objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Given array has null reference.");
        }

        int objectsNumber = objects.length;

        for (int objectIndex = 0; objectIndex < objectsNumber; objectIndex++) {
            if (objects[objectIndex] == null) {
                throw new IllegalArgumentException("Argument with index " + objectIndex + " of given array is null.");
            }
        }
    }

}
