package com.arek00.pacman.Graphics.Views.ConcreteViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Activities.Listeners.OnDrawListener;
import com.arek00.pacman.Activities.Listeners.OnDrawObservable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * View that handle displaying of all views like in-game view or menu view.
 */
public class GameView extends View implements OnDrawObservable, View.OnTouchListener {

    private Renderer renderer;
    private Context context;

    private List<OnDrawListener> listeners = new ArrayList<OnDrawListener>();

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
        informListeners();

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

    public void addListener(OnDrawListener listener) {
        NullPointerValidator.validate(listener);

        listeners.add(listener);
    }

    public void removeListener(OnDrawListener listener) {
        NullPointerValidator.validate(listener);

        listeners.remove(listener);
    }

    public void informListeners() {
        for (OnDrawListener listener : listeners) {
            listener.onDraw();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
