package com.arek00.pacman.Initializers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.MapRenderer;
import com.arek00.pacman.Logics.Fields.MapField;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Initialize basic map.
 */
public class MapInitializer {

    private MapRenderer mapRenderer;
    private IMap map;
    private ImageMapGenerator mapGenerator;
    private AssetsHelper helper;

    public MapInitializer(Context context) {
        helper = new AssetsHelper(context);

        this.mapRenderer = initializeMapRenderer();
        this.mapGenerator = new ImageMapGenerator();
    }


    public MapRenderer getMapRenderer() {
        return this.mapRenderer;
    }

    private MapField[] initializeTiles() {

        InputStream bitmapFile = null;


        try {
            bitmapFile = helper.getFileByName("images/pacman_sprites.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap atlas = BitmapFactory.decodeStream(bitmapFile);
        Tile[] tiles = {
                new Tile(atlas, 64, 128, 64, 64),
                new Tile(atlas, 64, 64, 64, 64),
                new Tile(atlas, 0, 64, 64, 64),
                new Tile(atlas, 0, 128, 64, 64),
                new Tile(atlas, 0, 128, 64, 64),
                new Tile(atlas, 0, 128, 64, 64)};

        MapField[] fields = {
                new MapField(tiles[0], true),
                new MapField(tiles[1], true),
                new MapField(tiles[2], true),
                new MapField(tiles[3], true),
                new MapField(tiles[4], true),
                new MapField(tiles[5], true)};

        return fields;
    }

    private IMap initializeMap() {

        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/map1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap mapScheme = BitmapFactory.decodeStream(bitmapStream);
        ImageMapGenerator imageMapGenerator = new ImageMapGenerator();
        IMap map = imageMapGenerator.generateMap("Basic Map", mapScheme);

        return map;
    }

    private MapRenderer initializeMapRenderer() {
        MapRenderer mapRenderer = new MapRenderer(initializeMap(), initializeTiles(), 64);
        return mapRenderer;
    }


}
