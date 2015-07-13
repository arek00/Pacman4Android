package com.arek00.pacman.Inputs;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 * Abstraction to handling user inputs like touches, accelerometer data etc.
 */
public interface InputHandler {
    /**
     * Get movement that given character should does.
     *
     * @param player
     * @return
     */
    public PointF convertInputToMovement(PointF input, ICharacter player);



}
