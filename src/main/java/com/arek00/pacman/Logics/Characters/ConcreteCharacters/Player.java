package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.IPlayer;


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
        if (startingLife < 0) {
            throw new IllegalArgumentException("Lifes amount can not be negative number");
        }

        this.playerPosition = playerPosition;
        this.life = startingLife;
        this.speed = speed;
    }

    public void setPosition(PointF position) {
        this.playerPosition = position;
    }

    public PointF getPosition() {
        return playerPosition;
    }

    public void move(PointF movement) {
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
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
