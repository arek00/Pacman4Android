package com.arek00.pacman.Graphics.Listeners;

/**
 * Created by Admin on 2015-08-10.
 */
public interface BallsRemainingObservable {

    public void addBallsRemainingListener(BallsRemainingListener listener);
    public void removeBallsRemainingListener(BallsRemainingListener listener);
    public void informBallsRemainingListener();
}
