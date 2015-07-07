package com.arek00.pacman.Logics.Maps.Generators;

import android.graphics.Bitmap;
import com.arek00.pacman.Logics.Maps.ConcreteMap.Map;
import com.arek00.pacman.Logics.Maps.Generators.ValueObjects.ColorsMap;
import com.arek00.pacman.Logics.Maps.IMap;

/**
 * Generate IMap object from Bitmap, that stores info about map in specified way.
 */
public class ImageMapGenerator {

    private Bitmap mapScheme;
    private int[][] fieldsPlan;
    private int mapHeight;
    private int mapWidth;

    /**
     * Generate Map object from Bitmap with defined
     */
    public ImageMapGenerator() {

    }

    public Map generateMap(String name, Bitmap mapScheme) {
        this.mapScheme = mapScheme;
        mapHeight = this.mapScheme.getHeight();
        mapWidth = this.mapScheme.getWidth();
        fieldsPlan = new int[mapWidth][mapHeight];

        fillPlan(mapWidth, mapHeight);

        return new Map(name, fieldsPlan, mapWidth, mapHeight);
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
        return ColorsMap.getValue(pixelValue);
    }
}
