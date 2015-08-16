package com.arek00.pacman.Graphics.Views.ConcreteViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Activities.Listeners.OnTickListener;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * View that handle displaying of all views like in-game view or menu view.
 */
public class GameView extends View {

    private Renderer renderer;
    private Context context;

    public GameView(Context context, Renderer renderer) {
        super(context);
        NullPointerValidator.validate(renderer);
        NullPointerValidator.validate(context);

        this.context = context;
        this.renderer = renderer;
    }

    @Override
    public void onDraw(Canvas canvas) {
        invalidate();
        refreshCanvas(canvas);
        doDraw(canvas);

        // Log.i("ONDRAW", "DRAW FRAME");
    }

    public void setRenderer(Renderer renderer) {
        NullPointerValidator.validate(renderer);
        this.renderer = renderer;
    }

    private void doDraw(Canvas canvas) {
        canvas.scale(GraphicsConfig.getMapScale(), GraphicsConfig.getMapScale());
        renderer.draw(canvas);
    }

    private void refreshCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

}
