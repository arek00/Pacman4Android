package com.arek00.pacman.Graphics.Listeners.Points;

/**
 */
public interface PointsObservable {
    public void addPointsListener(PointsListener listener);
    public void removePointsListener(PointsListener listener);
    public void informPointsListeners();
}
