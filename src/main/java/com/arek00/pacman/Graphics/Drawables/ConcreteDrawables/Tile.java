package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * One tile that holds bitmap.
 */
public class Tile implements Drawable {
    private Bitmap bitmap;
    private Paint paint = new Paint();

    public Tile(Bitmap bitmap) {
        NullPointerValidator.validate(bitmap);
        this.bitmap = bitmap;
    }

    public Tile(Bitmap bitmap, int x, int y, int width, int height) {
        NullPointerValidator.validate(bitmap);
        NumberValidator.checkNegativeNumber(x);
        NumberValidator.checkNegativeNumber(y);
        NumberValidator.checkNegativeNumber(width);
        NumberValidator.checkNegativeNumber(height);
        NumberValidator.checkNumberIsZero(width);
        NumberValidator.checkNumberIsZero(height);

        this.bitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
    }

    public void draw(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        draw(canvas, 0, 0);
    }

    public void draw(Canvas canvas, float x, float y) {
        NullPointerValidator.validate(canvas);
        NumberValidator.checkNegativeNumber(x);
        NumberValidator.checkNegativeNumber(y);

        canvas.drawBitmap(this.bitmap, x, y, this.paint);
    }
}
