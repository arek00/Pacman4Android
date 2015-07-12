package com.arek00.pacman.Graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.arek00.pacman.Graphics.Listeners.OnDrawListener;
import com.arek00.pacman.Graphics.Listeners.OnDrawObservable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Initializers.LevelInitializer;
import com.arek00.pacman.Inputs.ConcreteHandlers.TouchHandler;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import javax.management.ListenerNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * View that handle displaying of all views like ingame view or menu view.
 */
public class MainView extends View implements OnDrawObservable {

    private Renderer renderer;
    private TouchHandler handler;
    private ILevel level;
    private LevelInitializer levelInitializer;

    private List<OnDrawListener> listeners = new ArrayList<OnDrawListener>();

    public MainView(Context context) {
        super(context);
        handler = new TouchHandler();
        setOnTouchListener(handler);

        levelInitializer = new LevelInitializer(context, handler);
        this.renderer = levelInitializer.getRenderer();
    }

    @Override
    public void onDraw(Canvas canvas) {
        doDraw(canvas);
        informListeners();
    }

    private void doDraw(Canvas canvas) {
        canvas.scale(0.25f, 0.25f, 0f, 0f);
        renderer.draw(canvas);
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
}
