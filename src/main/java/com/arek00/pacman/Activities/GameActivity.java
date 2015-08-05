package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeListener;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeObservable;
import com.arek00.pacman.Graphics.Listeners.Points.PointsListener;
import com.arek00.pacman.Graphics.Listeners.Points.PointsObservable;
import com.arek00.pacman.Graphics.Views.ConcreteViews.GameView;
import com.arek00.pacman.Initializers.NormalLevelInitializer;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.KeyHandler;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;
import com.arek00.pacman.R;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;

public class GameActivity extends Activity implements PointsListener, LifeListener {

    private IGame game;
    private GameView view;
    private KeyHandler inputHandler;
    private LabelRedrawer redrawer;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenResolution();
        setContentView(R.layout.game);

        initialize(this);
        game.startGame();
        setTitle(R.string.app_name);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(com.arek00.pacman.R.menu.main, menu);
//        return true;
//    }


    /**
     * Initialize all needed objects
     *
     * @param context
     */
    private void initialize(Context context) {

        NormalLevelInitializer initializer = new NormalLevelInitializer(context);
        this.inputHandler = new KeyHandler();
        this.view = new GameView(this, initializer.getInitializedRenderer());
        this.view.setRenderer(initializer.getInitializedRenderer());
        this.view.addListener(new TimeHelper());

        FrameLayout layout = (FrameLayout) findViewById(R.id.mainGameView);
        layout.addView(view);

        LifeObservable lifeObservable = (LifeObservable) initializer.getInitializedLevel();
        PointsObservable pointsObservable = (PointsObservable) initializer.getInitializedLevel();
        lifeObservable.addLifeListener(this);
        pointsObservable.addPointsListener(this);
        TextView livesView = (TextView) findViewById(R.id.lifesNumber);
        TextView pointsView = (TextView) findViewById(R.id.pointsNumber);
        redrawer = new LabelRedrawer(livesView, pointsView);

        this.game = new Game(initializer.getInitializedLevel(), view, inputHandler, this);
    }


//    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//        Log.i("ACTIVITY OnTouch", "Touch at: X: " + motionEvent.getX() + " Y: " + motionEvent.getY());
//        PointF input = new PointF(motionEvent.getX(), motionEvent.getY());
//
//        game.movePlayer();
//
//        return false;
//    }


    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        inputHandler.onKeyDown(i, keyEvent);

        return false;
    }

    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        inputHandler.onKeyUp(i, keyEvent);

        return false;
    }

    private void setScreenResolution() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        Point resolution = new Point();
        display.getMetrics(metrics);
        resolution.set(metrics.widthPixels, metrics.heightPixels);
        GraphicsConfig.setScreenSize(resolution);
    }

    public void onChangePoints(int currentPointsNumber) {
        redrawer.setPoints(currentPointsNumber);
        runOnUiThread(redrawer);
    }

    public void onChangeLife(int currentLifeNumber) {
        redrawer.setLives(currentLifeNumber);
        runOnUiThread(redrawer);
    }


    class LabelRedrawer implements Runnable {
        private TextView livesLabel;
        private TextView pointsLabel;
        private int points;
        private int lives;

        public LabelRedrawer(TextView livesLabel, TextView pointsLabel) {
            this.livesLabel = livesLabel;
            this.pointsLabel = pointsLabel;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public void setLives(int lives) {
            this.lives = lives;
        }

        public void run() {
            livesLabel.setText(Integer.toString(this.lives));
            pointsLabel.setText(Integer.toString(this.points));
        }
    }
}

