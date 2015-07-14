package com.arek00.pacman.Logics.Characters;

/**
 *
 */
public enum MovementDirection {

    NONE(0),
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4);

    public final int value;

    private MovementDirection(int value) {
        this.value = value;
    }

}
