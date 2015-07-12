package com.arek00.pacman.Initializers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Log;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.SimpleLevelRenderer;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class LevelInitializer {

    private ILevel level;
    private Renderer levelRenderer;
    private Context applicationContext;
    private AssetsHelper helper;
    private InputHandler handler;

    private final String DEFAULT_MAP_SCHEME = "images/map1.png";

    public LevelInitializer(Context context, InputHandler handler) {
        helper = new AssetsHelper(context);
        applicationContext = context;
        this.handler = handler;
        initialize();
    }

    public void initialize() {
        this.level = initializeLevel();
        this.levelRenderer = initializeRenderer();
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


    private IPlayer initializePlayer() {
        IPlayer player = new Player(new PointF(0, 0), 5, 5);

        return player;
    }

    private ILevel initializeLevel() {
        ILevel level = new NormalGameLevel(
                initializeMap(),
                initializePlayer(),
                handler
        );

        return level;
    }


    //IMap map, IPlayer player, InputHandler input
    public ILevel getInitializedLevel() {
        return this.level;
    }

    public Renderer getRenderer() {
        return this.levelRenderer;
    }

    private Renderer initializeRenderer() {
        //TODO define MapTiles
        Renderer renderer = new SimpleLevelRenderer(getInitializedLevel(), initializePlayer(getInitializedLevel()),);
        return renderer;
    }

    public Drawable initializePlayer(ILevel level) {
        Tile playerTile;
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/pacman_sprites.png");
        } catch (IOException e) {
            Log.e("LEVEL INITIALIZER ERROR", "Couldnt load image.");
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);

        Drawable player = new DrawableCharacter(level.getPlayer(), new Tile(bitmap, 64, 0, 64, 64));

        return player;
    }

}
