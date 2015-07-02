package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;

/**
 * Draw simple circle
 */
public class CircleDrawable implements Drawable {

    private float radius;
    private PointF position;
    private Paint paint;

    public CircleDrawable(float r, PointF position, int color) {
        this.radius = r;
        this.position = position;
        this.paint = createPaint(color);
    }

    public CircleDrawable(float r, float x, float y, int color) {
        this.radius = r;
        this.position = new PointF(x, y);
        this.paint = createPaint(color);
    }

    public void setColor(int color) {
        this.paint.setColor(color);
    }

    private Paint createPaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(position.x, position.y, radius, paint);
    }
}
