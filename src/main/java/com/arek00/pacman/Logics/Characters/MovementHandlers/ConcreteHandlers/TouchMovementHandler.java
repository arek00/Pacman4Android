package com.arek00.pacman.Logics.Characters.MovementHandlers.ConcreteHandlers;

import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementHandlers.IMovementHandler;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 *
 */
public class TouchMovementHandler implements IMovementHandler, View.OnTouchListener {

    ICharacter player;
    MovementDirection direction;

    public TouchMovementHandler(ICharacter player) {
        NullPointerValidator.validate(player);
        this.player = player;
    }


    public MovementDirection movePlayer() {
        return direction;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.direction = findMoveDirection(motionEvent.getX(), motionEvent.getY());
        return false;
    }

    private MovementDirection findMoveDirection(float touchX, float touchY) {
        float convertedTouchX = convertTouchToMapPoint(touchX);
        float convertedTouchY = convertTouchToMapPoint(touchY);

        float touchToPlayerDistanceX = convertedTouchX - player.getPosition().x;
        float touchToPlayerDistanceY = convertedTouchY - player.getPosition().y;

        if (Math.abs(touchToPlayerDistanceX) > Math.abs(touchToPlayerDistanceY)) {
            return findHorizontalMove(player.getPosition().x, touchToPlayerDistanceX);
        } else if (Math.abs(touchToPlayerDistanceX) < Math.abs(touchToPlayerDistanceY)) {
            return findVerticalMove(player.getPosition().y, touchToPlayerDistanceY);
        } else {
            return findVerticalMove(player.getPosition().x, touchToPlayerDistanceX);
        }
    }

    private MovementDirection findHorizontalMove(float playerX, float touchedX) {
        if (playerX < touchedX) {
            return MovementDirection.RIGHT;
        } else if (playerX > touchedX) {
            return MovementDirection.LEFT;
        }

        return MovementDirection.NONE;
    }

    private MovementDirection findVerticalMove(float playerY, float touchedY) {

        if (playerY < touchedY) {
            return MovementDirection.DOWN;
        } else if (playerY > touchedY) {
            return MovementDirection.UP;
        }

        return MovementDirection.NONE;
    }


    private float convertTouchToMapPoint(float point) {
        return point / GraphicsConfig.getTileSize() * GraphicsConfig.getMapScale();
    }
}
