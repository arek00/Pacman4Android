package com.arek00.pacman.Inputs.Handlers.ConcreteHandlers;

import android.graphics.PointF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 *
 */
public class KeyHandler implements InputHandler, KeyEvent.Callback {

    //W
    //SAD

    private final int UP = KeyEvent.KEYCODE_W;
    private final int DOWN = KeyEvent.KEYCODE_S;
    private final int LEFT = KeyEvent.KEYCODE_A;
    private final int RIGHT = KeyEvent.KEYCODE_D;

    private PointF keys = new PointF(0, 0);

    /**
     * Returns -1 or 1 values when key from axle is pressed or 0 when not
     *
     * @return
     */
    public PointF getInput() {
        return keys;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("KEY HANDLER", "KEY DOWN" + keyEvent.getCharacters());

        switch (keyEvent.getKeyCode()) {
            case UP:
                keys.y = 1f;
                break;
            case DOWN:
                keys.y = -1f;
                break;
            case LEFT:
                keys.x = -1f;
                break;
            case RIGHT:
                keys.x = 1f;
                break;
            default:
                break;
        }

        return false;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("KEY HANDLER", "KEY UP" + keyEvent.getCharacters());

        switch (keyEvent.getKeyCode()) {
            case UP:
                keys.y = 0;
                break;
            case DOWN:
                keys.y = 0;
                break;
            case LEFT:
                keys.x = 0;
                break;
            case RIGHT:
                keys.x = 0;
                break;
            default:
                break;
        }

        return false;
    }

    public boolean onKeyMultiple(int i, int i1, KeyEvent keyEvent) {
        return false;
    }
}