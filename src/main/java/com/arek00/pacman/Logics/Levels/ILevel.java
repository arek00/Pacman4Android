package com.arek00.pacman.Logics.Levels;

import com.arek00.pacman.Logics.Levels.LevelStrategies.ILevelStrategy;

/**
 * Abstraction of game level.
 */
public interface ILevel {

    /**
     * Set operation executed on start of level.
     *
     * @param startLevelStrategy
     */
    public void setStartLevelStrategy(ILevelStrategy startLevelStrategy);

    /**
     * Set operation executed after accept conditions of finish game
     *
     * @param finishLevelStrategy
     */
    public void setFinishLevelStrategy(ILevelStrategy finishLevelStrategy);

    /**
     * Set statement that finish game with win or lose.
     *
     * @param finishLevelStatement
     */
    public void setFinishLevelStatement(ILevelStrategy finishLevelStatement);

    public void startLevel();

    public void finishLevel();

    public void isFinished();
}
