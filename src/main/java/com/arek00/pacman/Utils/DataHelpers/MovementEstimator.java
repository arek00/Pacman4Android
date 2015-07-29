package com.arek00.pacman.Utils.DataHelpers;

import android.graphics.PointF;
import android.util.Log;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 *
 */
public class MovementEstimator {

    public static int calculateVector(float currentCoordinate, float destinationCoordinate) {

        if (destinationCoordinate > currentCoordinate) {
            return 1;
        } else if (destinationCoordinate < currentCoordinate) {
            return -1;
        }

        return 0;
    }

    public static int calculateVector(MovementDirection direction) {
        int vector = 0;

        if (direction == MovementDirection.DOWN ||
                direction == MovementDirection.RIGHT) {
            vector = 1;
        } else if (direction.value != MovementDirection.NONE.value) {
            vector = -1;
        }

        return vector;
    }


    public static int calculateHorizontalVector(MovementDirection direction) {
        int vector = 0;

        if (direction == MovementDirection.RIGHT) {
            vector = 1;
        } else if (direction == MovementDirection.LEFT) {
            vector = -1;
        }

        return vector;
    }

    public static int calculateVerticalVector(MovementDirection direction) {
        int vector = 0;

        if (direction == MovementDirection.DOWN) {
            vector = 1;
        } else if (direction == MovementDirection.UP) {
            vector = -1;
        }

        return vector;
    }

    public static PointF calculateMove(ICharacter character, MovementDirection direction, float delta) {

        int horizontalDirection = MovementEstimator.calculateHorizontalVector(direction);
        int verticalDirection = MovementEstimator.calculateVerticalVector(direction);

        float estimatedMoveX = character.getSpeed() * delta;
        float estimatedMoveY = estimatedMoveX;

//        Log.i("Movement", direction.toString());
//        Log.i("ESTIMATED", estimatedMoveX + " " + estimatedMoveY);

        estimatedMoveX = (estimatedMoveX > 1) ? 1f : estimatedMoveX;
        estimatedMoveY = (estimatedMoveY > 1) ? 1f : estimatedMoveY;

        estimatedMoveX *= horizontalDirection;
        estimatedMoveY *= verticalDirection;

        return new PointF(estimatedMoveX, estimatedMoveY);
    }

    public static float calculateActualPlayerMove(float playerPosition, float destinationPosition, float estimatedMove) {

        if (Math.abs(destinationPosition - playerPosition) < Math.abs(estimatedMove)) {
            return destinationPosition - playerPosition;
        }

        return estimatedMove;
    }

}
