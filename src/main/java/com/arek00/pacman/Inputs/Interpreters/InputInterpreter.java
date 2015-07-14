package com.arek00.pacman.Inputs.Interpreters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Abstraction to interprete information received from handlers.
 */
public interface InputInterpreter {

    /**
     * Get input from user and translate it to direction of player movement.
     *
     * @param inputData
     * @return
     */
    public MovementDirection interpretInputData(PointF inputData);
}
