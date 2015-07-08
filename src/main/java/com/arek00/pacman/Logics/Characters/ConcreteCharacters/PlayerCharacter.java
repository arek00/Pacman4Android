package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;


/**
 * Basic class that describes actions of player.
 */
public class PlayerCharacter implements ICharacter {

    private PointF playerPosition;
    private IMovementStrategy movementStrategy;
    private int life;
    private int points = 0;
    private boolean boosted = false;


    public PlayerCharacter(PointF playerPosition, int startingLife, IMovementStrategy movementStrategy) {
        NullPointerValidator.validate(movementStrategy);
        if (startingLife < 0) {
            throw new IllegalArgumentException("Lifes amount can not be negative number");
        }


        this.playerPosition = playerPosition;
        this.movementStrategy = movementStrategy;
        this.life = startingLife;
    }

    public void setPosition(PointF position) {
        this.playerPosition = position;
    }

    public PointF getPosition() {
        return playerPosition;
    }

    public void move(PointF movement) {
        movementStrategy.moveInformation(movement);
        movementStrategy.executeMove(this.playerPosition);
    }

    public void setMovementStrategy(IMovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isBoosted() {
        return boosted;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
