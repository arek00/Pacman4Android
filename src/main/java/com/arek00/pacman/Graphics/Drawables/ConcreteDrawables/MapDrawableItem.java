package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.*;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;

/**
 * Draw simple circle
 */
public class MapDrawableItem implements Drawable {

    private Bitmap bitmap;
    private Paint paint;

    public MapDrawableItem(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.paint = new Paint();
    }

    /**
     * Add tile from given atlas.
     *
     * @param bitmap
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public MapDrawableItem(Bitmap bitmap, int x, int y, int width, int height) {
        this.bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
    }


    public void draw(Canvas canvas, float x, float y) {
        canvas.drawBitmap(this.bitmap, 0, 0, this.paint);
    }

    public void draw(Canvas canvas) {
        draw(canvas, 0, 0);
    }
}
