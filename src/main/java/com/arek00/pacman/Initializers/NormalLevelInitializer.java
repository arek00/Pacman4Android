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
import com.arek00.pacman.Inputs.Interpreters.ConcreteInterpreters.KeyInterpreter;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Enemy;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Characters.MovementStrategies.ConcreteStrategies.RandomMovementStrategy;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.Generators.ImageMapGenerator;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.io.IOException;
import java.io.InputStream;

/**
 * Initialize:
 * - player, enemies,
 * - map,
 * - renderer,
 * - graphic elements like tiles,
 * - input interpreters
 */
public class NormalLevelInitializer {

    private ILevel level;
    private Renderer levelRenderer;
    private IPlayer player;
    private Drawable drawablePlayer;
    private IMap map;
    private Bitmap tileSheet;
    private Drawable[] mapTiles;
    private InputInterpreter interpreter;
    private IEnemy[] enemies;
    private Drawable[] enemiesDrawables;

    private Context applicationContext;
    private AssetsHelper helper;

    private final String DEFAULT_MAP_SCHEME = "images/map1.png";
    private final String TILESHEET = "images/pacman_sprites.png";
    private InputInterpreter initializedInterpreter;

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
     * - ENEMIES
     * - The rest
     */

    public void initialize() {
        this.tileSheet = getInitializedTileSheet();
        this.mapTiles = getInitializedMapTiles();
        this.map = getInitializedMap();
        this.player = getInitializedPlayer();
        this.drawablePlayer = getInitializedPlayerDrawable();
        this.enemies = initializeEnemies();
        this.enemiesDrawables = initializeDrawableEnemies(getInitializedEnemies());
        this.interpreter = getInitializedInterpreter();
        this.level = getInitializedLevel();
        this.levelRenderer = getInitializedRenderer();
    }

    private ILevel initializeLevel(IMap map, IPlayer player, IEnemy[] enemies, InputInterpreter interpreter) {
        ILevel level = new NormalGameLevel(map, player, enemies, interpreter);

        NullPointerValidator.validate(level);
        return level;
    }

    public ILevel getInitializedLevel() {
        if (this.level == null) {
            this.level = initializeLevel(getInitializedMap(), getInitializedPlayer(), getInitializedEnemies(), getInitializedInterpreter());
        }
        return this.level;
    }

    private Renderer initializeRenderer(ILevel level, Drawable player, Drawable[] enemies, Drawable[] mapTiles) {
        SimpleLevelRenderer renderer = new SimpleLevelRenderer(level, player, enemies, mapTiles);

        NullPointerValidator.validate(renderer);
        return renderer;
    }

    public Renderer getInitializedRenderer() {
        if (this.levelRenderer == null) {
            this.levelRenderer =
                    initializeRenderer(getInitializedLevel(), getInitializedPlayerDrawable(), getInitializedDrawableEnemies(), getInitializedMapTiles());
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

    public IMap getInitializedMap() {
        if (this.map == null) {
            this.map = initializeMap();
        }

        return this.map;
    }

    private IPlayer initializePlayer() {
        IPlayer player = new Player(new PointF(0, 0), 5, 10);

        NullPointerValidator.validate(player);
        return player;
    }

    public IPlayer getInitializedPlayer() {
        if (this.player == null) {
            this.player = initializePlayer();
        }

        NullPointerValidator.validate(this.player);
        return this.player;
    }


    private IEnemy[] initializeEnemies() {
        IPlayer player = getInitializedPlayer();
        IEnemy[] enemies = new IEnemy[]{
                new Enemy(new PointF(0, 0), 10, new RandomMovementStrategy(20, 100), player),
                new Enemy(new PointF(0, 0), 10, new RandomMovementStrategy(100, 200), player),
                new Enemy(new PointF(0, 0), 10, new RandomMovementStrategy(5, 50), player),
                new Enemy(new PointF(0, 0), 10, new RandomMovementStrategy(1000, 2000), player)};

        return enemies;
    }

    private IEnemy[] getInitializedEnemies() {
        if (this.enemies == null) {
            this.enemies = initializeEnemies();
        }

        return this.enemies;
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

    public Bitmap getInitializedTileSheet() {
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

    public Drawable[] getInitializedMapTiles() {
        if (this.mapTiles == null) {
            this.mapTiles = initializeMapTiles(getInitializedTileSheet());
        }

        return this.mapTiles;
    }

    private Drawable initializeDrawablePlayer(IPlayer player) {
        NullPointerValidator.validate(player);

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

    public Drawable getInitializedPlayerDrawable() {
        if (this.drawablePlayer == null) {
            this.drawablePlayer = initializeDrawablePlayer(this.player);
        }

        return drawablePlayer;
    }


    public Drawable[] initializeDrawableEnemies(IEnemy[] enemies) {
        NullPointerValidator.validate(enemies);

        Tile[] enemiesTiles;
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName("images/ghosts_sprites.png");
        } catch (IOException e) {
            Log.e("LEVEL INITIALIZER ERROR", "Couldnt load ghosts sprites image.");
            e.printStackTrace();
        }

        NullPointerValidator.validate(bitmapStream);
        Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);

        enemiesTiles = new Tile[]{
                new Tile(bitmap, 1000, 100, 64, 64),
                new Tile(bitmap, 1000, 164, 64, 64),
                new Tile(bitmap, 1000, 228, 64, 64),
                new Tile(bitmap, 1000, 296, 64, 64)};

        int enemiesNumber = enemies.length;
        int tilesNumber = enemiesTiles.length;


        Drawable[] enemiesDrawable = new DrawableCharacter[enemiesNumber];

        for (int iterator = 0; iterator < enemiesNumber; iterator++) {
            enemiesDrawable[iterator] = new DrawableCharacter(enemies[iterator],
                    enemiesTiles[iterator % tilesNumber]);
        }

        return enemiesDrawable;
    }

    public Drawable[] getInitializedDrawableEnemies() {
        if (this.enemiesDrawables == null) {
            this.enemiesDrawables = initializeDrawableEnemies(getInitializedEnemies());
        }

        return this.enemiesDrawables;
    }

    public InputInterpreter initializeInterpreter() {
        return new KeyInterpreter();
    }

    public InputInterpreter getInitializedInterpreter() {
        if (this.interpreter == null) {
            this.interpreter = initializeInterpreter();
        }

        return this.interpreter;
    }
}
