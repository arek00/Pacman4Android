package com.arek00.pacman.Logics.Maps.ConcreteMap;

import android.graphics.Bitmap;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Maps.IMap;

/**
 * Map that
 */
public class ImageMap implements IMap {

    private Bitmap mapScheme;
    private int[][] fieldsPlan;
    private int mapHeight;
    private int mapWidth;
    private final Drawable[] tiles;


    /**
     * Draw map from image. Colors fields definition are located in ImageMapFields.
     *
     * @param mapScheme
     * @param tiles
     */
    public ImageMap(Bitmap mapScheme, Drawable[] tiles) {
        this.mapScheme = mapScheme;
        this.tiles = tiles;
        mapHeight = this.mapScheme.getHeight();
        mapWidth = this.mapScheme.getWidth();
        fieldsPlan = new int[mapWidth][mapHeight];
        fillPlan(mapWidth, mapHeight);
    }

    private void fillPlan(int width, int height) {
        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                fieldsPlan[column][line] = getValueFromPixel(column, line);
            }
        }
    }

    private int getValueFromPixel(int x, int y) {
        int pixelValue = mapScheme.getPixel(x, y);
        for (ImageMapFields value : ImageMapFields.values()) {
            if (value.getColor() == pixelValue) {
                return value.getValue();
            }
        }
        return ImageMapFields.UNKNOWN.getValue();
    }

    public Drawable getField(int x, int y) {
        int drawableIndex = fieldsPlan[x][y];
        return tiles[drawableIndex];
    }

    public int getMapHeight() {
        return this.mapHeight;
    }

    public int getMapWidth() {
        return this.mapWidth;
    }
}
