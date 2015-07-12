package com.arek00.pacman.Logics.Fields;

import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * Simplest implementation of IField
 */
public class Field implements IField {

    private int value;
    private boolean collide;

    public Field(int index, boolean collide) {
        this.value = index;
        this.collide = collide;
    }

    public int getValue() {
        return value;
    }

    public boolean isCollide() {
        return collide;
    }
}
