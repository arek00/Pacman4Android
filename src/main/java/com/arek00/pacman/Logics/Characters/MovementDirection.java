package com.arek00.pacman.Logics.Characters;

/**
 *
 */
public enum MovementDirection {

    NONE(0, 0),
    UP(1, 2),
    DOWN(2, 1),
    LEFT(3, 4),
    RIGHT(4, 3);

    public final int value;
    public final int reverse;

    private MovementDirection(int value, int reverse) {
        this.value = value;
        this.reverse = reverse;
    }

    /**
     * Return direction that contains given value (for example to get reverse value).
     * Return NONE direction when given value does not contain in enum.
     *
     * @param value
     * @return
     */
    public static MovementDirection getDirectionByValue(int value) {
        for (MovementDirection direction : values()) {
            if (direction.value == value)
                return direction;
        }

        return MovementDirection.NONE;
    }

    @Override
    public String toString() {
        switch (value) {
            case 0:
                return "NONE";
            case 1:
                return "UP";
            case 2:
                return "DOWN";
            case 3:
                return "LEFT";
            case 4:
                return "RIGHT";
            default:
                return "NONE";
        }
    }
}
