package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.Canvas;
import android.util.Log;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Logics.Fields.MapField;
import com.arek00.pacman.Logics.Maps.Generators.ValueObjects.ColorsMap;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Draw the game map on given canvas.
 */
public class MapRenderer extends Renderer {


    private IMap map;
    private final int tileSize;
    private MapField[] fields;

    /**
     * @param map        Model of map
     * @param fieldTiles Tiles to draw by renderer. Order of object in map is important. Array can't be smaller than
     * @param tileSize   Size of tile on canvas. Preferred size should be power of 2.
     */
    public MapRenderer(IMap map, MapField[] fieldTiles, int tileSize) {
        if (fieldTiles.length < ColorsMap.getFieldsKindsCount()) {
            throw new IllegalArgumentException("Array of tiles is too short, some of tiles are undefined.");
        }

        if (tileSize <= 0) {
            throw new IllegalArgumentException("Size of Tile must be positive.");
        }

        NullPointerValidator.validate(map);
        NullPointerValidator.validate(fieldTiles);


        this.map = map;
        this.tileSize = tileSize;
        fields = fieldTiles;
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
        int tile = map.getField((int) x, (int) y);
        Log.d("Draw tile: ", x + " " + y + " " + tile);
        fields[tile].draw(canvas, x * tileSize, y * tileSize);
    }
}
