package com.arek00.pacman.Logics.Characters;

import android.graphics.PointF;

/**
 *
 */
public interface ICharacter {

    //TODO Change idea of steering characters. Characters should be steer by level which gives more flexibility like
    //easy settings and access to all characters. Level should convert user actions and inputs, from accelerometer, keys,
    //touchscreen etc, to effective player move on the level matrix.

    /**
     * Set position of this character
     *
     * @param position
     */
    public void setPosition(PointF position);

    /**
     * Get position of this character
     *
     * @return
     */
    public PointF getPosition();

    /**
     * Move this character
     */
    public void move(MovementDirection direction);

    public float getSpeed();

    public void setSpeed(float speed);

}
