package com.arek00.pacman.Logics.Fields;

import android.graphics.Canvas;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;

/**
 * Representation of field on the map.
 */
public class MapField implements Drawable, IField {
    private boolean collide;
    private Tile fieldTile;

    public MapField(Tile fieldTile, boolean collide) {
        this.collide = collide;
        this.fieldTile = fieldTile;
    }

    public boolean isCollide() {
        return collide;
    }

    public void draw(Canvas canvas) {
        fieldTile.draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        fieldTile.draw(canvas, x, y);
    }
}
