package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;


/**
 * Basic class that describes actions of player.
 */
public class Player implements IPlayer {

    private PointF playerPosition;
    private int life;
    private int points = 0;
    private boolean boosted = false;
    private float speed;


    public Player(PointF playerPosition, int startingLife, float speed) {
        NullPointerValidator.validate(playerPosition);
        NumberValidator.checkNegativeNumber(startingLife);
        NumberValidator.checkNegativeNumber(speed);
        NumberValidator.checkNumberIsZero(startingLife);
        NumberValidator.checkNumberIsZero(speed);

        this.playerPosition = playerPosition;
        this.life = startingLife;
        this.speed = speed;
    }

    public void setPosition(PointF position) {
        NullPointerValidator.validate(position);

        this.playerPosition = position;
    }

    public PointF getPosition() {
        return playerPosition;
    }

    public void move(PointF movement) {
        NullPointerValidator.validate(movement);


        this.playerPosition.x += movement.x;
        this.playerPosition.y += movement.y;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        NumberValidator.checkNumberIsZero(speed);
        NumberValidator.checkNegativeNumber(speed);

        this.speed = speed;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int pointsAmount) {

        this.points += pointsAmount;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void addLife(int lifeAmount) {
        this.life += lifeAmount;
    }

    public void loseLife() {
        this.life--;
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
        NumberValidator.checkNegativeNumber(life);
        NumberValidator.checkNumberIsZero(life);

        this.life = life;
    }
}
