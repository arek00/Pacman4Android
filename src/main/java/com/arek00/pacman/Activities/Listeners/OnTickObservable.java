package com.arek00.pacman.Activities.Listeners;

/**
 * Observable on draw object
 */
public interface OnTickObservable {
    public void addListener(OnTickListener listener);
    public void removeListener(OnTickListener listener);
    public void informListeners();
}
