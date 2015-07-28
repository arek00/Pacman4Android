package com.arek00.pacman.Logics.Characters;

import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

/**
 *  Abstraction to enemy object in-game.
 */
public interface IEnemy extends ICharacter {

    public void setTarget(ICharacter character);
    public void setMovementStrategy(IMovementStrategy movementStrategy);
    public MovementDirection executeMove();
}
