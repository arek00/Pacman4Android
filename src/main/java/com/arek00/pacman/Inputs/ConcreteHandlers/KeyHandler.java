package com.arek00.pacman.Inputs.ConcreteHandlers;

import android.graphics.PointF;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.arek00.pacman.Inputs.InputConverter;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 *
 */
public class KeyHandler implements InputHandler, InputConverter, View.OnKeyListener {

    //W
    //SAD

    private final int UP = KeyEvent.KEYCODE_W;
    private final int DOWN = KeyEvent.KEYCODE_S;
    private final int LEFT = KeyEvent.KEYCODE_A;
    private final int RIGHT = KeyEvent.KEYCODE_D;

    private int verticalMovement = 0;
    private int horizontalMovement = 0;


    public PointF convertToPlayerMove(ICharacter player) {
        return getInput();
    }


    public PointF getInput() {
        return new PointF(horizontalMovement, verticalMovement);
    }

    public PointF getActualInput() {
        return getInput();
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.i("PRESS DOWN", Integer.toString(keyEvent.getUnicodeChar()));


        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {


            switch (keyEvent.getKeyCode()) {
                case UP:
                    verticalMovement = -1;
                    break;
                case DOWN:
                    verticalMovement = 1;
                    break;
                case LEFT:
                    horizontalMovement = -1;
                    break;
                case RIGHT:
                    horizontalMovement = 1;
                    break;
                default:
                    verticalMovement = 0;
                    horizontalMovement = 0;
                    break;
            }
        } else if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
            switch (keyEvent.getKeyCode()) {
                case UP:
                    verticalMovement = 0;
                    break;
                case DOWN:
                    verticalMovement = 0;
                    break;
                case LEFT:
                    horizontalMovement = 0;
                    break;
                case RIGHT:
                    horizontalMovement = 0;
                    break;
                default:
                    verticalMovement = verticalMovement;
                    horizontalMovement = horizontalMovement;
                    break;
            }
        }

        return false;
    }
}