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
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.io.IOException;
import java.io.InputStream;

/**
 * Initialize ILevel object and Renderer of it.
 */
public class NormalLevelInitializer {

    private ILevel level;
    private Renderer levelRenderer;
    private IPlayer player;
    private Drawable drawablePlayer;
    private IMap map;
    private Bitmap tileSheet;
    private Drawable[] mapTiles;


    private Context applicationContext;
    private AssetsHelper helper;

    private final String DEFAULT_MAP_SCHEME = "images/map1.png";
    private final String TILESHEET = "images/pacman_sprites.png";

    public NormalLevelInitializer(Context context) {
        NullPointerValidator.validate(context);

        helper = new AssetsHelper(context);
        applicationContext = context;
        initialize();
    }


    /*
     * - LEVEL
     * - LEVEL RENDERER
     * - MAP
     * - PLAYER
     * - The rest
     */

    public void initialize() {
        this.tileSheet = initializeTileSheet();
        this.mapTiles = initializeMapTiles(getInitializedTileSheet());
        this.map = initializeMap();
        this.player = initializePlayer();
        this.drawablePlayer = initializeDrawablePlayer(getInitializedPlayer());
        this.level = initializeLevel(getInitializedMap(), getInitializedPlayer());
        this.levelRenderer = initializeRenderer(getInitializedLevel(), getInitializedPlayerDrawable(), getInitializedMapTiles());
    }

    private ILevel initializeLevel(IMap map, IPlayer player) {
        ILevel level = new NormalGameLevel(map, player);

        NullPointerValidator.validate(level);
        return level;
    }

    private ILevel getInitializedLevel() {
        if (this.level == null) {
            this.level = initializeLevel(getInitializedMap(), getInitializedPlayer());
        }
        return this.level;
    }

    private Renderer initializeRenderer(ILevel level, Drawable player, Drawable[] mapTiles) {
        SimpleLevelRenderer renderer = new SimpleLevelRenderer(level, player, mapTiles);

        NullPointerValidator.validate(renderer);
        return renderer;
    }

    public Renderer getInitializedRenderer() {
        if (this.levelRenderer == null) {
            this.levelRenderer =
                    initializeRenderer(getInitializedLevel(), getInitializedPlayerDrawable(), getInitializedMapTiles());
        }

        return this.levelRenderer;
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

        NullPointerValidator.validate(map);

        return map;
    }

    private IMap getInitializedMap() {
        if (this.map == null) {
            this.map = initializeMap();
        }

        return this.map;
    }

    private IPlayer initializePlayer() {
        IPlayer player = new Player(new PointF(0, 0), 5, 5);

        NullPointerValidator.validate(player);
        return player;
    }

    private IPlayer getInitializedPlayer() {
        if (this.player == null) {
            this.player = initializePlayer();
        }

        return this.player;
    }


    /**
     * @return
     */
    private Bitmap initializeTileSheet() {
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName(TILESHEET);
        } catch (IOException e) {
            Log.e("INITIALIZE ERROR", "Couldn't load tilesheet");
            e.printStackTrace();
        }

        Bitmap tileSheet = BitmapFactory.decodeStream(bitmapStream);

        NullPointerValidator.validate(tileSheet);
        return tileSheet;
    }

    private Bitmap getInitializedTileSheet() {
        if (this.tileSheet == null) {
            this.tileSheet = initializeTileSheet();
        }
        return this.tileSheet;
    }

    private Drawable[] initializeMapTiles(Bitmap tileSheet) {
        Drawable[] drawables = new Tile[]{
                new Tile(tileSheet, 64, 128, 64, 64), //WALL
                new Tile(tileSheet, 0, 128, 64, 64), //COLLECTED
                new Tile(tileSheet, 64, 64, 64, 64), //SMALLBALL
                new Tile(tileSheet, 0, 64, 64, 64), //BIGBALL
                new Tile(tileSheet, 0, 128, 64, 64), //PLAYER_SPAWN
                new Tile(tileSheet, 0, 128, 64, 64), //ENEMY_SPAWN
        };

        NullPointerValidator.validate(drawables);
        return drawables;
    }

    private Drawable[] getInitializedMapTiles() {
        if (this.mapTiles == null) {
            this.mapTiles = initializeMapTiles(getInitializedTileSheet());
        }

        return this.mapTiles;
    }

    private Drawable initializeDrawablePlayer(IPlayer player) {
        NullPointerValidator.validate(level);

        Tile playerTile;
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/pacman_sprites.png");
        } catch (IOException e) {
            Log.e("LEVEL INITIALIZER ERROR", "Couldnt load image.");
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);

        playerTile = new Tile(bitmap, 64, 0, 64, 64);
        Drawable playerDrawable = new DrawableCharacter(player, playerTile);

        NullPointerValidator.validate(player);
        return playerDrawable;
    }

    private Drawable getInitializedPlayerDrawable() {
        if (this.drawablePlayer == null) {
            this.drawablePlayer = initializeDrawablePlayer(this.player);
        }

        return drawablePlayer;
    }

}
