package com.arek00.pacman.Logics.Characters.MovementStrategies;

import android.graphics.PointF;

/**
 * Strategy to steering player or AI.
 */
public interface IMovementStrategy {
    public void executeMove(PointF characterPosition);

    /**
     * Information that defines move.
     * It might be player actions/inputs, ai target etc.
     *
     * @param information Point that contains informations about movement character has to do.
     *                    Put here info like accelerometer measurement, target position etc.
     */
    public void moveInformation(PointF information);
}
