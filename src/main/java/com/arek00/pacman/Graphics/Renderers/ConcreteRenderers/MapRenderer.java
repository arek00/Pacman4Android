package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Logics.Maps.IMap;

import java.util.List;

/**
 * Draw the game map on given canvas.
 */
public class MapRenderer extends Renderer {


    private IMap map;
    private int tileSize;

    public MapRenderer(IMap map, int tileSize) {
        this.map = map;
        this.tileSize = tileSize;
    }

    /**
     * Draw all tiles of map.
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (int line = 0; line < map.getMapHeight(); line++) {
            for (int column = 0; column < map.getMapWidth(); column++) {
                draw(canvas, column, line);
            }
        }
    }

    public void draw(Canvas canvas, float x, float y) {
        Drawable tile = map.getField((int) x, (int) y);
        tile.draw(canvas, x * tileSize, y * tileSize);
    }
}
