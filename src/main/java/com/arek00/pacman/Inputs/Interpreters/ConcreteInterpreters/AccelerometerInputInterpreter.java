package com.arek00.pacman.Inputs.Interpreters.ConcreteInterpreters;

import android.graphics.PointF;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Interpret input data from accelerometer as player movement.
 */
public class AccelerometerInputInterpreter implements InputInterpreter {


    public MovementDirection interpretInputData(PointF inputData) {
        return estimateMovementDirection(inputData);
    }

    private MovementDirection estimateMovementDirection(PointF inputData) {
        float absX = Math.abs(inputData.x);
        float absY = Math.abs(inputData.y);

        if (absX > absY) {
            if (inputData.x > 0) {
                return MovementDirection.RIGHT;
            } else {
                return MovementDirection.LEFT;
            }
        } else if (absX < absY) {
            if (inputData.y < 0) {
                return MovementDirection.UP;
            } else {
                return MovementDirection.DOWN;
            }
        } else {
            return MovementDirection.NONE;
        }
    }
}
