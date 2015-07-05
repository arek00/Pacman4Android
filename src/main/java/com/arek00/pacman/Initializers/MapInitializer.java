package com.arek00.pacman.Initializers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.MapRenderer;
import com.arek00.pacman.Logics.Maps.ConcreteMap.ImageMap;
import com.arek00.pacman.Logics.Maps.ConcreteMap.Tile;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Initialize basic map.
 */
public class MapInitializer {

    private MapRenderer mapRenderer;
    private AssetsHelper helper;

    public MapInitializer(Context context) {
        helper = new AssetsHelper(context);

        this.mapRenderer = initializeMapRenderer();
    }


    public MapRenderer getMapRenderer() {
        return this.mapRenderer;
    }

    private Tile[] initializeTiles() {

        InputStream bitmapFile = null;

        try {
            bitmapFile = helper.getFileByName("images/pacman_sprites.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap atlas = BitmapFactory.decodeStream(bitmapFile);
        Tile tiles[] = {
                new Tile(atlas, 64, 128, 64, 64),
                new Tile(atlas, 64, 64, 64, 64),
                new Tile(atlas, 0, 64, 64, 64),
                new Tile(atlas, 0, 128, 64, 64),
                new Tile(atlas, 0, 128, 64, 64),
                new Tile(atlas, 0, 128, 64, 64)};
        return tiles;
    }

    private ImageMap initializeMap() {

        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/map1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap mapScheme = BitmapFactory.decodeStream(bitmapStream);
        ImageMap imageMap = new ImageMap(mapScheme, initializeTiles());

        return imageMap;
    }

    private MapRenderer initializeMapRenderer() {
        MapRenderer mapRenderer = new MapRenderer(initializeMap(), 64);
        return mapRenderer;
    }


}
