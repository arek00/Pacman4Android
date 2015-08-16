package com.arek00.pacman.Config;

import android.graphics.Point;
import android.util.Log;

/**
 * Config of graphic
 */
public class GraphicsConfig {

    private static int tileSize = 64;
    private static float mapScale;
    private static int screenWidth = 0;
    private static int screenHeight = 0;


    public static int getTileSize() {
        return tileSize;
    }

    public static float getMapScale() {
        return mapScale;
    }

    public static Point getScreenSize() {
        return new Point(screenWidth, screenHeight);
    }

    public static void setScreenSize(Point size) {
        screenWidth = size.x;
        screenHeight = size.y;

        //estimated experimental
        mapScale = ((float)screenWidth)/2500;
        Log.e("MAP SCALE",Float.toString(mapScale));
    }
}
