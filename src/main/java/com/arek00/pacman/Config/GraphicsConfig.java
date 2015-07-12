package com.arek00.pacman.Config;

/**
 * Config of graphic
 */
public class GraphicsConfig {
    private static int tileSize = 64;
    private static float mapScale = 0.5f;


    public static int getTileSize() {
        return tileSize;
    }

    public static float getMapScale() {
        return mapScale;
    }

}
