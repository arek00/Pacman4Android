package com.arek00.pacman.Logics.Maps.ConcreteMap;

import android.graphics.Color;

/**
 * Definitions of fields on map.
 */
public enum ImageMapFields {
    UNKNOWN(-1, Color.TRANSPARENT),
    WALL(0, Color.BLACK),
    SMALLBALL(1, Color.WHITE),
    BIGBALL(2, Color.BLUE),
    COLLECTEDBALL(3, Color.YELLOW),
    PACMANSPAWN(4, Color.GREEN),
    GHOSTSSPAWN(5, Color.RED);

    private int value, color;

    private ImageMapFields(int value, int color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public int getColor() {
        return color;
    }
}
