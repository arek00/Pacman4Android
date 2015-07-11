package com.arek00.pacman.Logics.Game;

import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Levels.ILevel;


public class Game implements IGame {

    ICharacter player;
    ICharacter[] enemies;
    ILevel level;

    public Game(ILevel level, ICharacter player, ICharacter[] enemies) {
        this.level = level;
        this.player = player;
        this.enemies = enemies;
    }


    public void startGame() {
        level.startLevel();
    }

    public void finishGame() {
        level.finishLevel();
    }

    public void pauseGame() {

    }

    public void continueGame() {

    }
}
