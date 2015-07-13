package com.arek00.pacman.Inputs;

import android.graphics.PointF;

/**
 * Abstraction to handling user inputs like touches, accelerometer data etc.
 */
public interface InputHandler {
    /**
     * Get input from device
     *
     * @param <T>
     * @return
     */
    public PointF getInput();

    /**
     * Method should convert informations from handler to be ready to use in movement.
     */
    public PointF getActualInput();
}
