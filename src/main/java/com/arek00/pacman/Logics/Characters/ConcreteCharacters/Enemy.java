package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.DataHelpers.MovementEstimator;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * Basic implementation of Enemy
 */
public class Enemy implements IEnemy {

    private ICharacter target;
    private IMovementStrategy movementStrategy;
    private PointF position;
    private float speed;


    public Enemy(PointF startingPosition, float speed, IMovementStrategy movementStrategy, ICharacter target) {
        NullPointerValidator.validate(movementStrategy);
        NullPointerValidator.validate(target);
        NullPointerValidator.validate(startingPosition);
        NumberValidator.checkNegativeNumber(speed);


        this.position = startingPosition;
        this.speed = speed;
        this.movementStrategy = movementStrategy;
        this.target = target;
    }

    public void setTarget(ICharacter character) {
        NullPointerValidator.validate(character);

        this.target = character;
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy) {
        NullPointerValidator.validate(movementStrategy);

        this.movementStrategy = movementStrategy;
    }

    public MovementDirection executeMove() {
        return movementStrategy.executeMovement();
    }

    public void setPosition(PointF position) {
        NullPointerValidator.validate(position);

        setPosition(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        NumberValidator.checkNegativeNumber(x);
        NumberValidator.checkNegativeNumber(y);

        this.position.x = x;
        this.position.y = y;
    }

    public PointF getPosition() {
        return this.position;
    }

    public void move(MovementDirection direction) {
//        int stepVector = MovementEstimator.calculateVector(direction);
//        float estimatedMove = stepVector * speed * TimeHelper.getDeltaTime();
//
//        if (direction == MovementDirection.RIGHT ||
//                direction == MovementDirection.LEFT) {
//            position.x += estimatedMove;
//
//        } else if (direction != MovementDirection.NONE) {
//            position.y += estimatedMove;
//        }


        PointF move = MovementEstimator.calculateMove(this, direction, TimeHelper.getDeltaTime());
        this.position.x += move.x;
        this.position.y += move.y;

    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        NumberValidator.checkNegativeNumber(speed);

        this.speed = speed;
    }

    @Override
    public String toString() {
        return "The Enemy";
    }
}
