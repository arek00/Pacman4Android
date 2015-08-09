package com.arek00.pacman.Graphics.Listeners.Game;

/**
 */
public interface FinishGameObservable {

    public void addOnFinishGameListener(FinishGameListener listener);

    public void removeOnFinishGameListener(FinishGameListener listener);

    public void informOnFinishGameListeners();

}
