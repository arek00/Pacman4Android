package com.arek00.pacman.Activities.Listeners;

/**
 */
public interface FinishGameObservable {

    public void addOnFinishGameListener(FinishGameListener listener);

    public void removeOnFinishGameListener(FinishGameListener listener);

    public void informOnFinishGameListeners();

}
