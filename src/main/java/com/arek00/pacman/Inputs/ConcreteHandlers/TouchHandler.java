package com.arek00.pacman.Inputs.ConcreteHandlers;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Inputs.InputConverter;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Utils.DataHelpers.MovementEstimator;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Handler of touching screen
 */
public class TouchHandler implements InputHandler, InputConverter, View.OnTouchListener {

    private float lastTouchedX;
    private float lastTouchedY;

    public TouchHandler() {
        lastTouchedX = -1;
        lastTouchedY = -1;
    }

    /**
     * Return point of last touch.
     *
     * @return
     */
    public PointF getInput() {
        return new PointF(lastTouchedX, lastTouchedY);
    }

    /**
     * After listened view's been touched, touchedPoint is set a position on the screen where it's been touched.
     * You may know where they touched it.
     *
     * @param view
     * @param motionEvent
     * @return
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        NullPointerValidator.validate(view);
        NullPointerValidator.validate(motionEvent);

        this.lastTouchedX = motionEvent.getX();
        this.lastTouchedY = motionEvent.getY();

        Log.i("TOUCH Handler", "Touched screen at: " + lastTouchedX + " " + lastTouchedY);

        return true;
    }

    /**
     * Convert input to be compatible with game map.
     *
     * @return Destination point player should going.
     */
    public PointF getActualInput() {
        float destinationX, destinationY;

        destinationX = lastTouchedX / (GraphicsConfig.getTileSize() * GraphicsConfig.getMapScale());
        destinationY = lastTouchedY / (GraphicsConfig.getTileSize() * GraphicsConfig.getMapScale());

        return new PointF(destinationX, destinationY);
    }

    /**
     * Returns move that given player should does.
     *
     * @param character
     * @return
     */
    public PointF convertToPlayerMove(ICharacter character) {
        NullPointerValidator.validate(character);

        Log.i("Last Touch: ", "X: " + lastTouchedX
                + " Y: " + lastTouchedY);

        if (lastTouchedX < 0 || lastTouchedY < 0) {
            return new PointF(0.001f, 0.001f);
        }

        PointF movement = MovementEstimator.calculatePlayerMove(character, getActualInput(), TimeHelper.getDeltaTime());

        Log.i("Movement: ", "X: " + movement.x
                + " Y: " + movement.y);

        return movement;
    }
}
