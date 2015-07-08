package com.arek00.pacman.Logics.Characters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

/**
 *
 */
public interface ICharacter {

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
