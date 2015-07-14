package com.arek00.pacman.Inputs.Handlers;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 * Abstraction to Handlers of user's input. Its usually should be like listeners of screen touching, keyboard etc.
 */
public interface InputHandler {
    /**
     * Get current Input
     *
     * @return
     */
    public PointF getInput();

}
