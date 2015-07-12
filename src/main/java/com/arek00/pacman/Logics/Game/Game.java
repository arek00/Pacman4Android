package com.arek00.pacman.Logics.Game;

import android.util.Log;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;


public class Game implements IGame {

    private ILevel level;
    private final long intervalTime = (long) 1000 / 60;
    private Thread mainLoop;

    private boolean isPaused = false;
    private boolean isFinished = false;

    public Game(ILevel level) {
        this.level = level;
        mainLoop = new Thread(new MainLoopThread());
    }


    public void startGame() {
        isFinished = false;
        level.startLevel();
        runMainLoop();
    }

    public void finishGame() {
        if (level.isFinished()) {
            level.finishLevel();
        }
        isFinished = true;
    }

    public void pauseGame() {
        synchronized (mainLoop) {
            try {
                mainLoop.wait();
            } catch (InterruptedException e) {
                Log.e("PAUSE GAME", "Problem with wait main game loop");
                e.printStackTrace();
            }
        }
    }

    public void continueGame() {
        synchronized (mainLoop) {
            mainLoop.notify();
        }
    }

    public void setLevel(ILevel level) {
        NullPointerValidator.validate(level);
        this.level = level;
    }

    private void runMainLoop() {
        mainLoop.start();
    }

    private class MainLoopThread implements Runnable {

        public void run() {
            while (!isFinished) {
                level.update();

                try {
                    synchronized (this) {
                        wait(intervalTime);
                    }
                } catch (InterruptedException e) {
                    Log.e("MAIN GAME LOOP THREAD", "PROBLEM WITH WAIT THREAD");
                    e.printStackTrace();
                }
            }
        }
    }


}
