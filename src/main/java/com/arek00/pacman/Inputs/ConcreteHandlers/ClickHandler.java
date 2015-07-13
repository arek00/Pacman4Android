package com.arek00.pacman.Inputs.ConcreteHandlers;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import com.arek00.pacman.Inputs.InputConverter;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 *
 */
public class ClickHandler implements InputHandler, InputConverter, View.OnClickListener {
    public PointF convertToPlayerMove(ICharacter player) {
        return null;
    }

    public PointF getInput() {
        return null;
    }

    public PointF getActualInput() {
        return null;
    }

    public void onClick(View view) {
        Log.i("Click Listener", "Asifnsdf");
    }
}
