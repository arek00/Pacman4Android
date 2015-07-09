package com.arek00.pacman.Logics.Characters.MovementStrategies.PlayerMovement;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;

/**
 * Move character by clicking on the screen.
 */
public class PointingMovement implements IMovementStrategy {

    private PointF characterPosition;
    private PointF information;
    private int movementSpeed;
    private int tileSize;

    public PointingMovement(int movementSpeed, int tileSize) {
        this.movementSpeed = movementSpeed;
        this.tileSize = tileSize;
    }

    public void executeMove(PointF characterPosition) {
        int horizontalVector = getVector(information.x, characterPosition.x);
        int verticalVector = getVector(information.y, characterPosition.y);

        float horizontalMove = horizontalVector * TimeHelper.getDeltaTime() * movementSpeed;
        float verticalMove = verticalVector * TimeHelper.getDeltaTime() * movementSpeed;

        characterPosition.x += calculateMaxStep(characterPosition.x, information.x, horizontalMove);
        characterPosition.y += calculateMaxStep(characterPosition.y, information.y, verticalMove);
    }

    /**
     * Information is point where user touched.
     *
     * @param information Point that contains informations about movement character has to do.
     */
    public void moveInformation(PointF information) {
        this.information.x = information.x;
        this.information.y = information.y;

        this.information.x /= tileSize;
        this.information.y /= tileSize;
    }

    private int getVector(float touchPosition, float characterPosition) {
        return (touchPosition > characterPosition) ? 1 : -1;
    }

    private float calculateMaxStep(float currentPosition, float destinationPosition, float estimatedStep) {
        if (destinationPosition - currentPosition < estimatedStep) {
            return destinationPosition - currentPosition;
        } else {
            return estimatedStep;
        }

    }
}
