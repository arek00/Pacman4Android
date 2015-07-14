package com.arek00.pacman.Logics.Characters.MovementHandlers;

import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Handle movement of player from different ways and inputs.
 */
public interface IMovementHandler {

    public MovementDirection movePlayer();
}
