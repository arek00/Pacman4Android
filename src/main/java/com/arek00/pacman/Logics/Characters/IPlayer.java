package com.arek00.pacman.Logics.Characters;

/**
 */
public interface IPlayer extends ICharacter {

    public int getPoints();

    public void addPoints(int pointsAmount);

    public void resetPoints();

    public int getLife();

    public void addLife(int lifeAmount);

    public void loseLife();
}
