package com.arek00.pacman.Factories;

import android.app.Activity;
import android.view.View;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;
import com.arek00.pacman.Logics.Levels.ILevel;

/**
 */
public class GameFactory {

    public static IGame createGame(ILevel level, View gameView, InputHandler inputHandler, Activity gameActivity) {
        IGame game = new Game(level, gameView, inputHandler, gameActivity);

        return game;
    }

}
