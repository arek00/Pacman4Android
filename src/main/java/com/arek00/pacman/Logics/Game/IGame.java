package com.arek00.pacman.Logics.Game;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;
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

    public ICharacter getPlayer();

    public void movePlayer(PointF move);

}
