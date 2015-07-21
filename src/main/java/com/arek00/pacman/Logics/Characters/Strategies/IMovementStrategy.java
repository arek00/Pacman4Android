package com.arek00.pacman.Logics.Characters.Strategies;

import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Abstraction to enemy movement strategy.
 */
public interface IMovementStrategy {
    public MovementDirection executeMovement();

}
