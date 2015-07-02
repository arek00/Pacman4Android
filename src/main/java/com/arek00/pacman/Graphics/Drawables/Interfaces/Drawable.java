package com.arek00.pacman.Graphics.Drawables.Interfaces;

import android.graphics.Canvas;

/**
 * Interface that note object as renderable in application.
 */
public interface Drawable {

    /**
     * Draw this Drawable object on given canvas
     *
     * @param canvas
     */
    public void draw(Canvas canvas);
}
