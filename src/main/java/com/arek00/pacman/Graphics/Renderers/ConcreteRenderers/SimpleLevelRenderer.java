package com.arek00.pacman.Graphics.Renderers.ConcreteRenderers;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MapTileField;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.ILevel;

/**
 * Renderer of level.
 */
public class SimpleLevelRenderer implements Renderer, ILevel {

    private ILevel level;
    private DrawableCharacter player;
    private DrawableCharacter[] enemies;
    private MapTileField[] fields;


    public SimpleLevelRenderer(ILevel level, Drawable playerDrawable, Drawable[] mapTiles) {
        this.level = level;
        this.player = new DrawableCharacter(level.getPlayer(), playerDrawable);
        initializeFields(mapTiles);
    }

    private void initializeFields(Drawable[] mapTiles) {
        this.fields = new MapTileField[mapTiles.length];

        for (int i = 0; i < mapTiles.length; i++) {
            this.fields[i] = new MapTileField(mapTiles[i], FieldsEnum.getFieldByIndex(i));
        }
    }


    public void draw(Canvas canvas) {
        drawMap(canvas);
        drawCharacters(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {

    }

    private void drawMap(Canvas canvas) {
        Point mapSize = new Point(level.getMapSize());

        for (int line = 0; line < mapSize.y; line++) {
            for (int column = 0; column < mapSize.x; column++) {
                int field = level.getCurrentMapFields()[column][line];
                fields[field].draw(canvas, column * GraphicsConfig.getTileSize(), line * GraphicsConfig.getTileSize());
            }
        }
    }

    private void drawCharacters(Canvas canvas) {
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

    public int[][] getCurrentMapFields() {
        return level.getCurrentMapFields();
    }

    public Point getMapSize() {
        return level.getMapSize();
    }

    public void setInputHandler(InputHandler input) {
        level.setInputHandler(input);
    }

    public void update() {
        level.update();
    }
}
