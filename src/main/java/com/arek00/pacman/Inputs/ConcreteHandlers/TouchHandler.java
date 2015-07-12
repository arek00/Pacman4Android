package com.arek00.pacman.Inputs.ConcreteHandlers;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Handler of touching screen
 */
public class TouchHandler implements InputHandler, View.OnTouchListener {

    private PointF touchedPoint;

    public TouchHandler() {
        touchedPoint = new PointF(-1, -1);
    }

    /**
     * Return point of last touch.
     *
     * @return
     */
    public PointF getInput() {
        return touchedPoint;
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

        touchedPoint.x = motionEvent.getX();
        touchedPoint.y = motionEvent.getY();

        return true;
    }

    /**
     * Convert input to be compatible with game map.
     *
     * @return Destination point player should going.
     */
    public PointF getActualInput() {
        float destinationX, destinationY;

        destinationX = touchedPoint.x / GraphicsConfig.getTileSize();
        destinationY = touchedPoint.y / GraphicsConfig.getTileSize();

        return new PointF(destinationX, destinationY);
    }
}
