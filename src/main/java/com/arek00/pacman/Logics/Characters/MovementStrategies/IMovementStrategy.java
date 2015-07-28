package com.arek00.pacman.Logics.Characters.MovementStrategies;

import com.arek00.pacman.Logics.Characters.MovementDirection;

/**
 * Abstraction to enemy movement strategy.
 */
public interface IMovementStrategy {
    public MovementDirection executeMovement();

}
