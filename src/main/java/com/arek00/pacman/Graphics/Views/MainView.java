package com.arek00.pacman.Graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.CircleDrawable;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.MapRenderer;
import com.arek00.pacman.Graphics.Renderers.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * View that handle displaying of all views like ingame view or menu view.
 */
public class MainView extends View {

    private Canvas mainCanvas;
    private List<Drawable> circles;
    private Renderer mapRenderer;

    public MainView(Context context) {
        super(context);
        initializeCanvas();
        initializeCircles();
        initializeRenderer();
    }

    @Override
    public void onDraw(Canvas canvas) {
        mapRenderer.draw(canvas);
    }

    private void initializeCircles() {
        circles = new ArrayList<Drawable>();
        circles.add(new CircleDrawable(30, 17, 8, Color.YELLOW));
        circles.add(new CircleDrawable(10, 25, 27, Color.MAGENTA));
        circles.add(new CircleDrawable(15, 72, 43, Color.GREEN));
    }

    private void initializeRenderer() {
        mapRenderer = new MapRenderer(circles);
    }

    private void initializeCanvas() {
        mainCanvas = new Canvas();
    }

    public Canvas getMainCanvas() {
        return mainCanvas;
    }
}
