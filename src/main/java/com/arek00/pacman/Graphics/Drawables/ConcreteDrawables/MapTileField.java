package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Fields.IField;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Representation of field on the map. Joins IField object and Tile graphic.
 */
public class MapTileField implements Drawable, IField {

    private IField field;
    private Drawable fieldTile;

    public MapTileField(Drawable fieldTile, IField field) {
        NullPointerValidator.validate(fieldTile);
        NullPointerValidator.validate(field);

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
        NullPointerValidator.validate(canvas);

        fieldTile.draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        NullPointerValidator.validate(canvas);

        fieldTile.draw(canvas, x, y);
    }
}
