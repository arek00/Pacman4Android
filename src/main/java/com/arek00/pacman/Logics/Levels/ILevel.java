package com.arek00.pacman.Logics.Levels;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.IPlayer;

/**
 * Abstraction of game level.
 */
public interface ILevel {

    public void startLevel();

    public boolean isFinished();

    public IPlayer getPlayer();

    public IEnemy[] getEnemies();

    public int getFieldValue(int x, int y);

    public Point getMapSize();

    /**
     * Set strategy to interprete input from user.
     *
     * @param interpreter
     */
    public void setInputInterpreter(InputInterpreter interpreter);

    /**
     * Put input from user to level
     *
     * @param input
     */
    public void setInput(PointF input);

    /**
     * Update a state of level.
     */
    public void update();
}
