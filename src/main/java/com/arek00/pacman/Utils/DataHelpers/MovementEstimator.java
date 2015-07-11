package com.arek00.pacman.Utils.DataHelpers;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;

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

    public static PointF calculatePlayerMove(ICharacter player, PointF destination, float delta) {

        int horizontalDirection = MovementEstimator.calculateVector(player.getPosition().x, destination.x);
        int verticalDirection = MovementEstimator.calculateVector(player.getPosition().y, destination.y);

        float estimatedMoveX = horizontalDirection * player.getSpeed() * delta;
        float estimatedMoveY = verticalDirection * player.getSpeed() * delta;

        estimatedMoveX = calculateActualPlayerMove(player.getPosition().x, destination.x, estimatedMoveX);
        estimatedMoveY = calculateActualPlayerMove(player.getPosition().y, destination.y, estimatedMoveY);

        return new PointF(estimatedMoveX, estimatedMoveY);
    }

    public static float calculateActualPlayerMove(float playerPosition, float destinationPosition, float estimatedMove) {

        if (Math.abs(destinationPosition - playerPosition) < Math.abs(estimatedMove)) {
            return destinationPosition - playerPosition;
        }

        return estimatedMove;
    }

}
