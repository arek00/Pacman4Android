package com.arek00.pacman.Logics.Characters.ConcreteCharacters;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementHandlers.IMovementHandler;
import com.arek00.pacman.Logics.Characters.Strategies.IMovementStrategy;
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

        this.position = position;
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
        int stepVector = MovementEstimator.calculateVector(direction);
        float estimatedMove = stepVector * speed * TimeHelper.getDeltaTime();

        if (direction.value == MovementDirection.RIGHT.value ||
                direction.value == MovementDirection.LEFT.value) {
            position.x += estimatedMove;


        } else if (direction.value == MovementDirection.UP.value ||
                direction.value == MovementDirection.DOWN.value) {
            position.y += estimatedMove;
        } else {
            position.x += estimatedMove;
        }

    }

    public float getSpeed() {
        return 0;
    }

    public void setSpeed(float speed) {
        NumberValidator.checkNegativeNumber(speed);

        this.speed = speed;
    }
}
