package com.arek00.pacman.Initializers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class LevelInitializer {

    private ILevel level;
    private Context applicationContext;
    private AssetsHelper helper;

    private final String DEFAULT_MAP_SCHEME = "images/map1.png";

    public LevelInitializer(Context context) {
        helper = new AssetsHelper(context);
    }

    private IMap initializeMap() {
        ImageMapGenerator generator = new ImageMapGenerator();
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName(DEFAULT_MAP_SCHEME);
        } catch (IOException e) {
            Log.e("LEVEL INITIALIZER ERROR", "Couldn't initialize map");
            e.printStackTrace();
        }

        Bitmap schemeImage = BitmapFactory.decodeStream(bitmapStream);
        IMap map = generator.generateMap("Basic Map", schemeImage);

        return map;
    }


    //IMap map, IPlayer player, ICharacter enemies, InputHandler input
    public ILevel getInitializedLevel() {
        new NormalGameLevel()

        return level;
    }

}
