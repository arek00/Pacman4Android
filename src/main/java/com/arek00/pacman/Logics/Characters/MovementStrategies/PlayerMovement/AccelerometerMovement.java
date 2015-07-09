package com.arek00.pacman.Logics.Characters.MovementStrategies.PlayerMovement;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

/**
 * Steering by using built-in accelerometer.
 */
public class AccelerometerMovement implements IMovementStrategy {

    private PointF playerInput;
    private PointF accelerometerInfo;

    /**
     * Put measurements of accelerometer to
     *
     * @param playersInput
     */
    public void setPlayersInputMove(PointF playersInput) {
        this.playerInput = playerInput;
    }


    public void executeMove(PointF characterPosition) {
        throw new UnsupportedOperationException("Accelerometer move not yet implemented");

        //TODO implement Accelerometer move when players will be correctly displayed.
    }

    /**
     * As argument pass PointF that holds measurements from X and Y accelerometer sensors.
     *
     * @param information Point that contains informations about movement character has to do.
     */
    public void moveInformation(PointF information) {
        accelerometerInfo = information;
    }
}
