package com.arek00.pacman.Logics.Game;

import com.arek00.pacman.Logics.Levels.ILevel;

/**
 *
 */
public interface IGame {

    public void startGame();

    public void finishGame();

    public void pauseGame();

    public void continueGame();

    public void setLevel(ILevel level);

}
