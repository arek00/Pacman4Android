package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Fields.IField;

/**
 * Representation of field on the map. Joins IField object and Tile graphic.
 *
 */
public class MapTileField implements Drawable, IField {

    private IField field;
    private Tile fieldTile;

    public MapTileField(Tile fieldTile, IField field) {
        this.field = field;
        this.fieldTile = fieldTile;
    }

    public int getValue() {
        return this.field.getValue();
    }

    public boolean isCollide() {
        return this.field.isCollide();
    }

    public void draw(Canvas canvas) {
        fieldTile.draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        fieldTile.draw(canvas, x, y);
    }
}
