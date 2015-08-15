package com.arek00.pacman.Factories;

import android.content.Context;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MapTileField;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.SimpleLevelRenderer;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Inputs.Interpreters.ConcreteInterpreters.AccelerometerInputInterpreter;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Creates level and level renderer
 */
public class LevelFactory {


    /**
     * Create level from parameters given from player.
     *
     * @param mapID        - index of chosen level
     * @param enemiesCount - number of chosen enemies
     * @param livesCount   - number of chosen lives amount
     * @param context      - context of application
     * @return
     */
    public static ILevel createLevel(int mapID, int enemiesCount, int livesCount, Context context) {
        IMap map = MapFactory.createMap(mapID, context);
        IPlayer player = PlayerFactory.createPlayer(livesCount);
        IEnemy[] enemies = EnemiesFactory.createEnemies(enemiesCount, player);
        InputInterpreter interpreter = new AccelerometerInputInterpreter();
        ILevel level = new NormalGameLevel(map, player, enemies, interpreter);

        return level;
    }

    /**
     * Build level renderer from given level instance
     *
     * @param level   - Level to build renderer
     * @param context - Context of application
     * @return
     */
    public static Renderer createLevelRenderer(ILevel level, Context context) {
        DrawableCharacter playerDrawable = PlayerFactory.createDrawablePlayer(level.getPlayer(), context);
        DrawableCharacter[] enemiesDrawable = EnemiesFactory.createEnemies(level.getEnemies(), context);
        Drawable[] mapTiles = MapFactory.getMapTiles(context);

        Renderer mapRenderer = new SimpleLevelRenderer(level, playerDrawable, enemiesDrawable, mapTiles);
        return mapRenderer;
    }

}
