package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.*;
import android.util.Log;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MapTileField;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Graphics.Renderers.Utils.Occlusion;
import com.arek00.pacman.Graphics.Renderers.Utils.OcclusionEstimator;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Characters.MovementHandlers.IMovementHandler;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Renderer of level.
 */
public class SimpleLevelRenderer implements Renderer, ILevel {

    private ILevel level;
    private DrawableCharacter player;
    private DrawableCharacter[] enemies;
    private MapTileField[] fields;

    private float offsetX = 0, offsetY = 0;


    /**
     * Define renderer with given level, player drawable and array of map tiles drawables.
     * Map tiles drawables amount must be at least equal number of defined fields in FieldsEnum
     * except UNKNOWN_FIELD value. It means that for 6 defined fields in FieldsEnum (counting with UNKNOWN_FIELD, array
     * has to have at least 5 elements inside).
     *
     * @param level
     * @param playerDrawable
     * @param mapTiles
     */
    public SimpleLevelRenderer(ILevel level, Drawable playerDrawable, Drawable[] enemyDrawables, Drawable[] mapTiles) {
        NullPointerValidator.validate(level);
        NullPointerValidator.validate(playerDrawable);
        NullPointerValidator.validate(enemyDrawables);
        NullPointerValidator.validate(mapTiles);

        if (mapTiles.length < FieldsEnum.values().length - 1) {
            throw new IllegalArgumentException("mapTiles does not contains all required elements inside.");
        }

        this.level = level;
        this.player = new DrawableCharacter(level.getPlayer(), playerDrawable);

        initializeEnemies(enemyDrawables);
        initializeFields(mapTiles);
    }

    private void initializeFields(Drawable[] mapTiles) {
        NullPointerValidator.validate(mapTiles);

        this.fields = new MapTileField[mapTiles.length];

        for (int i = 0; i < mapTiles.length; i++) {
            this.fields[i] = new MapTileField(mapTiles[i], FieldsEnum.getFieldByIndex(i));
        }
    }

    private void initializeEnemies(Drawable[] enemyDrawables) {
        this.enemies = new DrawableCharacter[enemyDrawables.length];
        ICharacter[] enemiesArray = level.getEnemies();

        for (int iterator = 0; iterator < enemyDrawables.length; iterator++) {
            this.enemies[iterator] = new DrawableCharacter(enemiesArray[iterator],
                    enemyDrawables[iterator]);
        }
    }


    public void draw(Canvas canvas) {
        NullPointerValidator.validate(canvas);
        calculateOffset();
        canvas.translate(offsetX, offsetY);

        drawMap(canvas);
        drawCharacters(canvas);
        drawOffsets(offsetX, offsetY, canvas);

    }

    private void drawOffsets(float offsetX, float offsetY, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);

        String text = String.format("OffsetX: %.2f, OffsetY: %.2f", offsetX, offsetY);
        canvas.drawText(text, -offsetX + 80, -offsetY + 80, paint);
    }

    public void draw(Canvas canvas, float x, float y) {
        NullPointerValidator.validate(canvas);

        throw new UnsupportedOperationException("Operation not supported. Renderer should be use by method without extra position parameters.");
    }

    private void drawMap(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        canvas.drawColor(Color.BLACK);

        Point mapSize = new Point(level.getMapSize());
        Occlusion lineOcclusion = OcclusionEstimator.getOcclusion(offsetY, GraphicsConfig.getScreenSize().y, mapSize.y);
        Occlusion columnOcclusion = OcclusionEstimator.getOcclusion(offsetX, GraphicsConfig.getScreenSize().x, mapSize.x);

        for (int line = lineOcclusion.getStartPosition(); line < lineOcclusion.getEndPosition(); line++) {
            for (int column = columnOcclusion.getStartPosition(); column < columnOcclusion.getEndPosition(); column++) {
                int field = level.getFieldValue(column, line);

                fields[field].draw(canvas, column * GraphicsConfig.getTileSize(), line * GraphicsConfig.getTileSize());
            }
        }
    }

    private void drawCharacters(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        int tileSize = GraphicsConfig.getTileSize();

        PointF position = player.getPosition();
        player.draw(canvas, position.x * tileSize, position.y * tileSize);

        for (DrawableCharacter enemy : enemies) {
            enemy.draw(canvas, enemy.getPosition().x * tileSize, enemy.getPosition().y * tileSize);
        }

        // Log.i("DRAW PlAYER", "X: " + position.x * GraphicsConfig.getTileSize() +
        //  " Y: " + position.y * GraphicsConfig.getTileSize());
    }

    public void startLevel() {
        level.startLevel();
    }

    public void finishLevel() {
        level.finishLevel();
    }

    public boolean isFinished() {
        return level.isFinished();
    }

    public ICharacter getPlayer() {
        return level.getPlayer();
    }

    public ICharacter[] getEnemies() {
        return level.getEnemies();
    }

    public int getFieldValue(int x, int y) {
        return level.getFieldValue(x, y);
    }

    public Point getMapSize() {
        return level.getMapSize();
    }

    public void setInputInterpreter(InputInterpreter interpreter) {
        this.level.setInputInterpreter(interpreter);
    }

    public void setInput(PointF input) {
        this.level.setInput(input);
    }

    public void update() {
        level.update();
    }

    private void calculateOffset() {
        float playerX = player.getPosition().x;
        float playerY = player.getPosition().y;
        Point screenSize = GraphicsConfig.getScreenSize();

        float playerOnScreenX = playerX * GraphicsConfig.getTileSize();
        float playerOnScreenY = playerY * GraphicsConfig.getTileSize();

        float centerX = (screenSize.x / 2) / GraphicsConfig.getMapScale();
        float centerY = (screenSize.y / 2) / GraphicsConfig.getMapScale();

        this.offsetX = centerX - playerOnScreenX;
        this.offsetY = centerY - playerOnScreenY;

    }

//    private void calculateOcclusions


//    private float validateOffsetX(float offset) {
//        int mapSize = getMapSize().x;
//        int tileSize = GraphicsConfig.getTileSize();
//        int screenSize = GraphicsConfig.getScreenSize().x;
//
//        if (offset > 0) {
//            offset = 0;
//        }
//
//        if (-offset > (mapSize * tileSize) -  screenSize)
//        return (mapSize * tileSize) -  screenSize;
//    }
}
