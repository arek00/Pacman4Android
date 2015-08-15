package com.arek00.pacman.Factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Logics.Maps.Managers.MapManager;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;
import com.arek00.pacman.Utils.DataHelpers.BitmapRetriever;

import java.io.IOException;
import java.io.InputStream;

/**
 */
public class MapFactory {

    public static Drawable[] getMapTiles(Context context) {
        AssetsHelper helper = new AssetsHelper(context);
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/pacman_sprites.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);

        Tile[] mapTiles = new Tile[]{new Tile(bitmap, 64, 128, 64, 64), //WALL
                new Tile(bitmap, 0, 128, 64, 64), //COLLECTED
                new Tile(bitmap, 64, 64, 64, 64), //SMALLBALL
                new Tile(bitmap, 0, 64, 64, 64), //BIGBALL
                new Tile(bitmap, 0, 128, 64, 64), //PLAYER_SPAWN
                new Tile(bitmap, 0, 128, 64, 64) //ENEMY_SPAWN
        };

        return mapTiles;
    }

    public static IMap createMap(int mapID, Context context) {
        ImageMapGenerator generator = new ImageMapGenerator();
        MapManager manager = new MapManager(context);
        BitmapRetriever bitmapRetriever = new BitmapRetriever(context);
        Bitmap mapScheme = bitmapRetriever.retrieveBitmapFromAssets(manager.getMapSchemePathById(mapID));

        IMap map = generator.generateMap("NONE", mapScheme);

        return map;
    }
}
