package com.arek00.pacman.Logics.Game;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import com.arek00.pacman.Graphics.Listeners.Game.FinishGameListener;
import com.arek00.pacman.Graphics.Listeners.Game.FinishGameObservable;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.util.ArrayList;
import java.util.List;


public class Game implements IGame, FinishGameObservable {

    private ILevel level;
    private final long intervalTime = (long) 1000 / 60;
    private Thread mainLoop;
    private View renderer;
    private Canvas canvas;
    private Activity activity;
    private InputHandler inputHandler;
    private List<FinishGameListener> onFinishGameListeners = new ArrayList<FinishGameListener>();

    private GameState state = GameState.PAUSED;

    public Game(ILevel level, View renderer, InputHandler input, Activity activity) {
        NullPointerValidator.validate(level);
        NullPointerValidator.validate(renderer);
        NullPointerValidator.validate(activity);
        NullPointerValidator.validate(input);

        this.level = level;
        mainLoop = new Thread(new MainLoopThread(new UIThread()));
        this.renderer = renderer;
        this.canvas = new Canvas();
        this.activity = activity;
        this.inputHandler = input;
    }


    public void startGame() {
        level.startLevel();
        runMainLoop();
    }

    public void finishGame() {
        state = GameState.FINISHED;
        informOnFinishGameListeners();
    }

    private boolean isFinishedGame() {
        return level.isFinished();
    }

    public void pauseGame() {
        state = GameState.PAUSED;
    }

    public void continueGame() {
        state = GameState.RUNNING;
    }

    public void setLevel(ILevel level) {
        NullPointerValidator.validate(level);
        this.level = level;
    }

    public View getRenderer() {
        return this.renderer;
    }

    public GameState getGameState() {
        return this.state;
    }

    public ICharacter getPlayer() {
        return level.getPlayer();
    }

    private void runMainLoop() {
        mainLoop.start();
    }


    public void addOnFinishGameListener(FinishGameListener listener) {
        NullPointerValidator.validate(listener);
        onFinishGameListeners.add(listener);
    }

    public void removeOnFinishGameListener(FinishGameListener listener) {
        NullPointerValidator.validate(listener);
        onFinishGameListeners.remove(listener);
    }

    public void informOnFinishGameListeners() {
        for (FinishGameListener listener : onFinishGameListeners) {
            listener.onFinishGame();
        }
    }

    private class MainLoopThread implements Runnable {
        private UIThread uiDrawThread;

        public MainLoopThread(UIThread uiDrawThread) {
            this.uiDrawThread = uiDrawThread;
        }

        public void run() {
            while (state != GameState.FINISHED) {

                PointF input = inputHandler.getInput();
                level.setInput(input);

                if (state == GameState.RUNNING) {
                    level.update();
                }
                activity.runOnUiThread(uiDrawThread);

                if (isFinishedGame()) {
                    finishGame();
                }

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
            renderer.draw(canvas);
        }
    }
}
