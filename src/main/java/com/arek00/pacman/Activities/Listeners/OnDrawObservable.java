package com.arek00.pacman.Activities.Listeners;

/**
 * Observable on draw object
 */
public interface OnDrawObservable {
    public void addListener(OnDrawListener listener);
    public void removeListener(OnDrawListener listener);
    public void informListeners();
}
