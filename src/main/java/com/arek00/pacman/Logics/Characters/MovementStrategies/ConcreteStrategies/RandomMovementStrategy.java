package com.arek00.pacman.Logics.Characters.MovementStrategies.ConcreteStrategies;

import android.util.Log;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.Validators.NumberValidator;

import java.util.Random;

/**
 * Choose random direction of movement for CPU-steering character.
 */
public class RandomMovementStrategy implements IMovementStrategy {

    private int stepsCount;
    private MovementDirection currentDirection;
    private Random random;
    private final int MAX_RANDOM = 5;
    private final int MAX_STEPS;
    private final int MIN_STEPS;

    /**
     * @param minimumSteps Define minimum number of steps character must take before random new direction
     * @param maximumSteps Define maximum amount of steps before randomize next direction.
     */
    public RandomMovementStrategy(int minimumSteps, int maximumSteps) {

        NumberValidator.checkNegativeNumber(minimumSteps);
        NumberValidator.checkNegativeNumber(maximumSteps);
        NumberValidator.checkNumberIsZero(minimumSteps);
        NumberValidator.checkNumberIsZero(maximumSteps);

        if (minimumSteps > maximumSteps) {
            throw new IllegalArgumentException("MinimumSteps value must be lower than maximum steps value");
        }

        this.random = new Random();
        this.MIN_STEPS = minimumSteps;
        this.MAX_STEPS = maximumSteps;
    }

    public MovementDirection executeMovement() {
        if (stepsCount <= 0) {
            getRandomNextStepDirection();
        }
        --stepsCount;

        //Log.i("ENEMY MOVE", this.currentDirection.toString() + "Steps remaining: " + stepsCount);

        return this.currentDirection;
    }

    private void getRandomNextStepDirection() {
        this.currentDirection = getRandomDirection();
        this.stepsCount = getRandomizeStepsCount();
    }


    private MovementDirection getRandomDirection() {
        int value = random.nextInt(MAX_RANDOM);

        return MovementDirection.getDirectionByValue(value);
    }

    private int getRandomizeStepsCount() {
        return random.nextInt(MAX_STEPS - MIN_STEPS) + MIN_STEPS;
    }

}
