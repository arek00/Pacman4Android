package com.arek00.pacman.Logics.Fields;

/**
 * Simplest implementation of IField
 */
public class Field implements IField {

    private int value;
    private boolean collide;

    public Field(int value, boolean collide) {
        this.value = value;
        this.collide = collide;
    }

    public int getValue() {
        return value;
    }

    public boolean isCollide() {
        return collide;
    }
}
