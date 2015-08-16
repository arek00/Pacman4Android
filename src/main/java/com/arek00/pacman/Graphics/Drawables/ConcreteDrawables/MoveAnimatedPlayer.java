package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.IPlayer;

/**
 */
public class MoveAnimatedPlayer extends MoveAnimatedCharacter implements IPlayer {

    private IPlayer player;

    public MoveAnimatedPlayer(IPlayer character, Drawable[] moveDrawableTiles) {
        super(character, moveDrawableTiles);
        this.player = character;
    }

    public int getPoints() {
        return player.getPoints();
    }

    public void addPoints(int pointsAmount) {
        player.addPoints(pointsAmount);
    }

    public void resetPoints() {
        player.resetPoints();
    }

    public int getLife() {
        return player.getLife();
    }

    public void addLife(int lifeAmount) {
        player.addLife(lifeAmount);
    }

    public void loseLife() {
        player.loseLife();
    }
}
