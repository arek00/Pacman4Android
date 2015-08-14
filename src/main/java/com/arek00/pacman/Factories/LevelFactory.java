package com.arek00.pacman.Factories;

import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MapTileField;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Graphics.Renderers.ConcreteRenderers.SimpleLevelRenderer;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 */
public class LevelFactory {

    public static ILevel createLevel(IMap map, IPlayer player, IEnemy[] enemies, InputInterpreter interpreter) {
        NullPointerValidator.validate(map);
        NullPointerValidator.validate(player);
        NullPointerValidator.validate(enemies);
        NullPointerValidator.validate(interpreter);

        ILevel level = new NormalGameLevel(map, player, enemies, interpreter);

        return level;
    }

    public static Renderer createLevelRenderer(ILevel level, Drawable playerDrawable, Drawable[] enemiesDrawable, Drawable[] mapTiles) {
        Renderer mapRenderer = new SimpleLevelRenderer(level, playerDrawable, enemiesDrawable, mapTiles);
        return mapRenderer;
    }

}
