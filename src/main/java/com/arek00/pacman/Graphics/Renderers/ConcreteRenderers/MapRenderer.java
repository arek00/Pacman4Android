package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.Renderer;

import java.util.List;

/**
 * Render map of the game.
 */
public class MapRenderer extends Renderer {

    public MapRenderer(List<Drawable> drawables) {
        super(drawables);
    }

    public void draw(Canvas canvas) {
        drawBackground(canvas);

        for (Drawable drawable : getDrawables()) {
            drawable.draw(canvas);
        }
    }

    private void drawBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        RectF rect = new RectF(0f, 0f, 100f, 100f);

        canvas.drawRect(rect, paint);
    }
}
