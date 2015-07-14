package com.arek00.pacman.Inputs.Interpreters.ConcreteInterpreters;

import android.graphics.PointF;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Interpret data from keyboard given from KeyHandler instance.
 */
public class KeyInterpreter implements InputInterpreter {

    /**
     * Interpret data given from KeyHandler.
     *
     * @param inputData
     * @return
     */
    public MovementDirection interpretInputData(PointF inputData) {

        switch ((int) inputData.x) {
            case -1:
                return MovementDirection.LEFT;
            case 1:
                return MovementDirection.RIGHT;
        }

        switch ((int) inputData.y) {
            case -1:
                return MovementDirection.DOWN;
            case 1:
                return MovementDirection.UP;
        }

        return MovementDirection.NONE;
    }
}
