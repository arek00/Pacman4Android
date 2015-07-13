package com.arek00.pacman.Logics.Levels;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Maps.IMap;

/**
 * Abstraction of game level.
 */
public interface ILevel {

    public void startLevel();

    public void finishLevel();

    public boolean isFinished();

    public ICharacter getPlayer();

    public ICharacter[] getEnemies();

    public int getFieldValue(int x, int y);

    public Point getMapSize();

    /**
     * Do on every step of game
     */
    public void update();
}
