package com.arek00.pacman.Graphics.Listeners.Lifes;

/**
 *
 */
public interface LifeObservable {
    public void addLifeListener(LifeListener listener);
    public void removeLifeListener(LifeListener listener);
    public void informLifeListeners();

}
