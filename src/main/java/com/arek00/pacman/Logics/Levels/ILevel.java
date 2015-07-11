package com.arek00.pacman.Logics.Levels;

import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 * Abstraction of game level.
 */
public interface ILevel {


    public void startLevel();

    public void finishLevel();

    public boolean isFinished();

    public ICharacter getPlayer();

    public ICharacter[] getEnemies();

    public void getInputFromPlayer(InputHandler input);

    /**
     * Do on every step of game
     */
    public void update();
}
