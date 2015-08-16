package com.arek00.pacman.Activities.Listeners;

/**
 */
public interface PointsObservable {
    public void addPointsListener(PointsListener listener);
    public void removePointsListener(PointsListener listener);
    public void informPointsListeners();
}
