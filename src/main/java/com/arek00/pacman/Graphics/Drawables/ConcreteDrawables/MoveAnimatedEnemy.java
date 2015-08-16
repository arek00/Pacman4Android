package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

/**
 * Created by Admin on 2015-08-16.
 */
public class MoveAnimatedEnemy extends MoveAnimatedCharacter implements IEnemy {

    private IEnemy enemy;

    public MoveAnimatedEnemy(IEnemy character, Drawable[] moveDrawableTiles) {
        super(character, moveDrawableTiles);
        this.enemy = character;
    }

    public void setTarget(ICharacter character) {
        this.enemy.setTarget(character);
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy) {
        this.enemy.setMovementStrategy(movementStrategy);
    }

    public MovementDirection executeMove() {
        return this.enemy.executeMove();
    }
}
