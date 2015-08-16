package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.arek00.pacman.Config.AccelerometerConfig;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Factories.LevelFactory;
import com.arek00.pacman.Activities.Listeners.BallsRemainingListener;
import com.arek00.pacman.Activities.Listeners.BallsRemainingObservable;
import com.arek00.pacman.Activities.Listeners.FinishGameListener;
import com.arek00.pacman.Activities.Listeners.FinishGameObservable;
import com.arek00.pacman.Activities.Listeners.LifeListener;
import com.arek00.pacman.Activities.Listeners.LifeObservable;
import com.arek00.pacman.Activities.Listeners.PointsListener;
import com.arek00.pacman.Activities.Listeners.PointsObservable;
import com.arek00.pacman.Graphics.Renderers.Renderer;
import com.arek00.pacman.Graphics.Views.ConcreteViews.GameView;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.AccelerometerHandler;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.KeyHandler;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.LevelScenarios.NormalGameLevel;
import com.arek00.pacman.R;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

public class GameActivity extends Activity implements PointsListener, LifeListener, FinishGameListener, BallsRemainingListener {

    public final static String POINTS_MESSAGE = "com.arek00.pacman.Activities.POINTS";
    public final static String LIVES_MESSAGE = "com.arek00.pacman.Activities.LIVES";

    private IGame game;
    private GameView view;
    private KeyHandler inputHandler;
    private GameViewRefresher refresher;
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

        AccelerometerConfig.loadSettings(getSharedPreferences(AccelerometerConfig.CALIBRATION_SETTINGS_FILE_NAME, Context.MODE_PRIVATE));
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
        initializeGame(context);
        initializeLayout();
        initializeLabels();
    }


    private void initializeGame(Context context) {

        Intent intent = getIntent();
        int levelID = intent.getIntExtra(SelectCustomGameActivity.LEVEL_ID_MESSAGE, 0);
        int livesAmount = intent.getIntExtra(SelectCustomGameActivity.STARTING_LIVES_MESSAGE, 3);
        int enemiesAmount = intent.getIntExtra(SelectCustomGameActivity.STARTING_ENEMIES_MESSAGE, 5);

        ILevel level = LevelFactory.createLevel(levelID, enemiesAmount, livesAmount, context);
        initializeListeners((NormalGameLevel) level);

        Renderer gameRenderer = LevelFactory.createLevelRenderer(level, context);
        this.view = new GameView(context, gameRenderer);
        this.view.addListener(new TimeHelper());

        InputHandler inputHandler = new AccelerometerHandler(context, 100);

        this.game = new Game(level, this.view, inputHandler, this);
        ((FinishGameObservable) this.game).addOnFinishGameListener(this);

    }

    private void initializeLayout() {
        FrameLayout layout = (FrameLayout) findViewById(R.id.mainGameView);
        layout.addView(view);
        View child = findViewById(R.id.pauseGameInfoButton);
        layout.bringChildToFront(child);
        pausedGameButtonVisibilityHandler = new ViewVisibilityHandler(child, layout);
    }

    private void initializeListeners(NormalGameLevel level) {
        LifeObservable lifeObservable = level;
        PointsObservable pointsObservable = level;
        BallsRemainingObservable ballsObservable = level;
        lifeObservable.addLifeListener(this);
        pointsObservable.addPointsListener(this);
        ballsObservable.addBallsRemainingListener(this);
    }

    private void initializeLabels() {
        TextView livesView = (TextView) findViewById(R.id.lifesNumber);
        TextView pointsView = (TextView) findViewById(R.id.pointsNumber);
        TextView remainingBallsView = (TextView) findViewById(R.id.ballsRemainingNumber);
        refresher = new GameViewRefresher(livesView, pointsView, remainingBallsView);
    }

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
        refresher.setPoints(currentPointsNumber);
        runOnUiThread(refresher);
    }

    public void onChangeLife(int currentLifeNumber) {
        refresher.setLives(currentLifeNumber);
        runOnUiThread(refresher);
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
        refresher.setBallsRemaining(ballsRemainingNumber);
        runOnUiThread(refresher);
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

