package com.arek00.pacman.Logics.Characters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

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
     *
     * @param x
     * @param y
     */
    public void move(PointF movement);

    public void setMovementStrategy(IMovementStrategy strategy);

}
