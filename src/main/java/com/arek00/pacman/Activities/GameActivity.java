package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Listeners.BallsRemainingListener;
import com.arek00.pacman.Graphics.Listeners.BallsRemainingObservable;
import com.arek00.pacman.Graphics.Listeners.Game.FinishGameListener;
import com.arek00.pacman.Graphics.Listeners.Game.FinishGameObservable;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeListener;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeObservable;
import com.arek00.pacman.Graphics.Listeners.Points.PointsListener;
import com.arek00.pacman.Graphics.Listeners.Points.PointsObservable;
import com.arek00.pacman.Graphics.Views.ConcreteViews.GameView;
import com.arek00.pacman.Initializers.NormalLevelInitializer;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.AccelerometerHandler;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.KeyHandler;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;
import com.arek00.pacman.R;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

public class GameActivity extends Activity implements PointsListener, LifeListener, FinishGameListener, BallsRemainingListener {

    public final static String POINTS_MESSAGE = "com.arek00.pacman.Activities.POINTS";
    public final static String LIVES_MESSAGE = "com.arek00.pacman.Activities.LIVES";

    private IGame game;
    private GameView view;
    private KeyHandler inputHandler;
    private GameViewRefresher redrawer;
    private ViewVisibilityHandler pausedGameButtonVisibilityHandler;

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

        initializeGame(context, initializer);
        initializeLayout();
        initializeListeners(initializer);

        TextView livesView = (TextView) findViewById(R.id.lifesNumber);
        TextView pointsView = (TextView) findViewById(R.id.pointsNumber);
        TextView remainingBallsView = (TextView) findViewById(R.id.ballsRemainingNumber);
        redrawer = new GameViewRefresher(livesView, pointsView, remainingBallsView);

        this.game = new Game(initializer.getInitializedLevel(), view, new AccelerometerHandler(this, 100), this);
        ((FinishGameObservable) this.game).addOnFinishGameListener(this);
    }


    private void initializeGame(Context context, NormalLevelInitializer initializer) {
        this.inputHandler = new KeyHandler();
        this.view = new GameView(this, initializer.getInitializedRenderer());
        this.view.setRenderer(initializer.getInitializedRenderer());
        this.view.addListener(new TimeHelper());
    }

    private void initializeLayout() {
        FrameLayout layout = (FrameLayout) findViewById(R.id.mainGameView);
        layout.addView(view);
        View child = findViewById(R.id.pauseGameInfoButton);
        layout.bringChildToFront(child);
        pausedGameButtonVisibilityHandler = new ViewVisibilityHandler(child, layout);
    }

    private void initializeListeners(NormalLevelInitializer initializer) {

        LifeObservable lifeObservable = (LifeObservable) initializer.getInitializedLevel();
        PointsObservable pointsObservable = (PointsObservable) initializer.getInitializedLevel();
        BallsRemainingObservable ballsObservable = (BallsRemainingObservable) initializer.getInitializedLevel();
        lifeObservable.addLifeListener(this);
        pointsObservable.addPointsListener(this);
        ballsObservable.addBallsRemainingListener(this);
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
        game.pauseGame();

        pausedGameButtonVisibilityHandler.setVisible();
        runOnUiThread(pausedGameButtonVisibilityHandler);
    }

    public void onResumeButtonClicked(View view) {
        game.continueGame();
        pausedGameButtonVisibilityHandler.setInvisible();

        runOnUiThread(pausedGameButtonVisibilityHandler);
    }

    public void onFinishGame() {
        int points = ((IPlayer) game.getPlayer()).getPoints();
        int lives = ((IPlayer) game.getPlayer()).getLife();

        Intent intent = new Intent(this, FinishGameActivity.class);
        intent.putExtra(POINTS_MESSAGE, points);
        intent.putExtra(LIVES_MESSAGE, lives);
        startActivity(intent);
        finish();

    }

    public void onBallsRemainingChanged(int ballsRemainingNumber) {
        redrawer.setBallsRemaining(ballsRemainingNumber);
        runOnUiThread(redrawer);
    }


    /**
     * Just runs activity refreshing on RunOnUIThread
     */
    class GameViewRefresher implements Runnable {
        private TextView ballsRemainingLabel;
        private TextView livesLabel;
        private TextView pointsLabel;
        private int points;
        private int lives;
        private int ballsRemaining;

        public GameViewRefresher(TextView livesLabel, TextView pointsLabel, TextView ballsRemainingLabel) {
            this.livesLabel = livesLabel;
            this.pointsLabel = pointsLabel;
            this.ballsRemainingLabel = ballsRemainingLabel;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public void setLives(int lives) {
            this.lives = lives;
        }

        public void setBallsRemaining(int ballsRemaining) {
            this.ballsRemaining = ballsRemaining;
        }

        public void run() {
            livesLabel.setText(Integer.toString(this.lives));
            pointsLabel.setText(Integer.toString(this.points));
            ballsRemainingLabel.setText(Integer.toString(ballsRemaining));
        }
    }

    class ViewVisibilityHandler implements Runnable {
        private View view;
        private FrameLayout layout;
        private int viewVisibility;

        public ViewVisibilityHandler(View view, FrameLayout layout) {
            NullPointerValidator.validate(view);
            NullPointerValidator.validate(layout);

            this.view = view;
            this.layout = layout;
            viewVisibility = View.VISIBLE;
        }

        public void setVisible() {
            viewVisibility = View.VISIBLE;
        }

        public void setInvisible() {
            viewVisibility = View.INVISIBLE;
        }

        public void run() {
            layout.bringChildToFront(this.view);
            view.setVisibility(this.viewVisibility);
        }
    }
}

