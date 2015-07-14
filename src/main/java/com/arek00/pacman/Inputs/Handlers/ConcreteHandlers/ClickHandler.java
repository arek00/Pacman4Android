package com.arek00.pacman.Inputs.Handlers.ConcreteHandlers;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 *
 */
public class ClickHandler {


    public void onClick(View view) {
        Log.i("Click Listener", "Asifnsdf");
    }

    public PointF getRawInput() {
        return null;
    }

    public PointF getPlayerMove(ICharacter player) {
        return null;
    }
}
