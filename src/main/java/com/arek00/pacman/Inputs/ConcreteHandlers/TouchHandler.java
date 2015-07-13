package com.arek00.pacman.Inputs.ConcreteHandlers;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Utils.DataHelpers.MovementEstimator;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Handler of touching screen
 */
public class TouchHandler implements InputHandler {
    /**
     * Return point of last touch.
     *
     * @return
     */

    public PointF convertInputToMovement(PointF input, ICharacter player) {
        return convertToPlayerMove(input, player);
    }

    /**
     * Convert input to be compatible with game map.
     *
     * @return Destination point player should going.
     */
    private PointF getActualInput(PointF input) {
        float destinationX, destinationY;

        destinationX = input.x / (GraphicsConfig.getTileSize() * GraphicsConfig.getMapScale());
        destinationY = input.y / (GraphicsConfig.getTileSize() * GraphicsConfig.getMapScale());

        Log.i("ACTUAL TOUCH", destinationX + " " + destinationY);

        return new PointF(destinationX, destinationY);
    }

    /**
     * Returns move that given player should does.
     *
     * @param character
     * @return
     */
    private PointF convertToPlayerMove(PointF input, ICharacter character) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(input);

        PointF actualInput = getActualInput(input);

        if (actualInput.x < 0 || actualInput.y < 0) {
            return new PointF(0f, 0f);
        }
        PointF movement = MovementEstimator.calculatePlayerMove(character, actualInput, TimeHelper.getDeltaTime());

        Log.i("Movement: ", "X: " + movement.x
                + " Y: " + movement.y);

        return movement;
    }
}
