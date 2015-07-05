package com.arek00.pacman.Graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.MapRenderer;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Initializers.MapInitializer;

/**
 * View that handle displaying of all views like ingame view or menu view.
 */
public class MainView extends View {

    private Renderer renderer;
    private MapInitializer initializer;

    public MainView(Context context) {
        super(context);
        initializer = new MapInitializer(context);
        renderer = initializer.getMapRenderer();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.scale(0.1f, 0.1f, 0f,0f);
        renderer.draw(canvas);

    }
}
