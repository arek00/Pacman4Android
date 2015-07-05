package com.arek00.pacman.Logics.Maps.ConcreteMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;

/**
 * One tile that might be draw on map.
 */
public class Tile implements Drawable {
    private Bitmap bitmap;
    private Paint paint = new Paint();

    public Tile(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Tile(Bitmap bitmap, int x, int y, int width, int height) {
        this.bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
    }

    public void draw(Canvas canvas) {
        draw(canvas, 0, 0);
    }

    public void draw(Canvas canvas, float x, float y) {
        canvas.drawBitmap(this.bitmap, x, y, this.paint);
    }
}
