package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import android.util.Log;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Utils.DataHelpers.MovementEstimator;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
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

        this.playerPosition.x = position.x;
        this.playerPosition.y = position.y;
    }

    public void setPosition(float x, float y) {
        NumberValidator.checkNegativeNumber(x);
        NumberValidator.checkNegativeNumber(y);

        this.playerPosition.x = x;
        this.playerPosition.y = y;
    }

    public PointF getPosition() {
        return playerPosition;
    }

    public void move(MovementDirection movement) {
        NullPointerValidator.validate(movement);

        doStep(movement);
    }

    private void doStep(MovementDirection direction) {

//        int stepVector = MovementEstimator.calculateVector(direction);
//        float estimatedMove = stepVector * speed * TimeHelper.getDeltaTime();

        PointF move = MovementEstimator.calculateMove(this, direction, TimeHelper.getDeltaTime());
        playerPosition.x += move.x;
        playerPosition.y += move.y;


//
//        if (direction.value == MovementDirection.RIGHT.value ||
//                direction.value == MovementDirection.LEFT.value) {
//            playerPosition.x += estimatedMove;
//
//
//        } else if (direction.value == MovementDirection.UP.value ||
//                direction.value == MovementDirection.DOWN.value) {
//            playerPosition.y += estimatedMove;
//        } else {
//            playerPosition.x += estimatedMove;
//        }

        // Log.i("PLAYER MOVEMENT", "Estimated move: " + estimatedMove + " Delta time: " + TimeHelper.getDeltaTime());
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

    @Override
    public String toString() {
        return "The Player";
    }
}
