package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MapTileField;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
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
    public SimpleLevelRenderer(ILevel level, Drawable playerDrawable, Drawable[] mapTiles) {
        NullPointerValidator.validate(level);
        NullPointerValidator.validate(playerDrawable);
        NullPointerValidator.validate(mapTiles);

        if (mapTiles.length < FieldsEnum.values().length - 1) {
            throw new IllegalArgumentException("mapTiles does not contains all required elements inside.");
        }

        this.level = level;
        this.player = new DrawableCharacter(level.getPlayer(), playerDrawable);
        initializeFields(mapTiles);
    }

    private void initializeFields(Drawable[] mapTiles) {
        NullPointerValidator.validate(mapTiles);

        this.fields = new MapTileField[mapTiles.length];

        for (int i = 0; i < mapTiles.length; i++) {
            this.fields[i] = new MapTileField(mapTiles[i], FieldsEnum.getFieldByIndex(i));
        }
    }


    public void draw(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        drawMap(canvas);
        drawCharacters(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        NullPointerValidator.validate(canvas);

        throw new UnsupportedOperationException("Operation not supported. Renderer should be use by method without extra position parameters.");
    }

    private void drawMap(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        canvas.drawColor(Color.BLACK);

        Point mapSize = new Point(level.getMapSize());

        for (int line = 0; line < mapSize.y; line++) {
            for (int column = 0; column < mapSize.x; column++) {
                int field = level.getFieldValue(column, line);

                fields[field].draw(canvas, column * GraphicsConfig.getTileSize(), line * GraphicsConfig.getTileSize());
            }
        }
    }

    private void drawCharacters(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        PointF position = player.getPosition();
        player.draw(canvas, position.x * GraphicsConfig.getTileSize(), position.y * GraphicsConfig.getTileSize());
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

    public void movePlayer(PointF playerMove) {
        level.movePlayer(playerMove);
    }

    public void update() {
        level.update();
    }
}
