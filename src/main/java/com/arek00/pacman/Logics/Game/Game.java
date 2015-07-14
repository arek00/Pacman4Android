package com.arek00.pacman.Logics.Game;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;


public class Game implements IGame {

    private ILevel level;
    private final long intervalTime = (long) 1000 / 60;
    private Thread mainLoop;
    private View view;
    private Canvas canvas;
    private Activity activity;
    private InputHandler inputHandler;

    private boolean isFinished = false;

    public Game(ILevel level, View view, InputHandler input, Activity activity) {
        NullPointerValidator.validate(level);
        NullPointerValidator.validate(view);
        NullPointerValidator.validate(activity);
        NullPointerValidator.validate(input);

        this.level = level;
        mainLoop = new Thread(new MainLoopThread(new UIThread()));
        this.view = view;
        this.canvas = new Canvas();
        this.activity = activity;
        this.inputHandler = input;
    }


    public void startGame() {
        isFinished = false;
        level.startLevel();
        runMainLoop();
    }

    public void finishGame() {
//        if (level.isFinished()) {
//            level.finishLevel();
//        }
//        isFinished = true;
    }

    public void pauseGame() {
//        synchronized (mainLoop) {
//            try {
//                mainLoop.wait();
//            } catch (InterruptedException e) {
//                Log.e("PAUSE GAME", "Problem with wait main game loop");
//                e.printStackTrace();
//            }
//        }
    }

    public void continueGame() {
        synchronized (mainLoop) {
            //mainLoop.notify();
        }
    }

    public void setLevel(ILevel level) {
        NullPointerValidator.validate(level);
        this.level = level;
    }

    public View getView() {
        return this.view;
    }

    public ICharacter getPlayer() {
        return level.getPlayer();
    }

    private void runMainLoop() {
        mainLoop.start();
    }

    private class MainLoopThread implements Runnable {
        private UIThread uiDrawThread;

        public MainLoopThread(UIThread uiDrawThread) {
            this.uiDrawThread = uiDrawThread;
        }

        public void run() {
            while (!isFinished) {

                PointF input = inputHandler.getInput();
                level.setInput(input);
                level.update();
                activity.runOnUiThread(uiDrawThread);

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

    private class UIThread implements Runnable {
        public void run() {
            view.draw(canvas);
        }
    }
}
