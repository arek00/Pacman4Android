package com.arek00.pacman;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.arek00.pacman.Graphics.Views.MainView;
import com.arek00.pacman.Initializers.NormalLevelInitializer;
import com.arek00.pacman.Inputs.ConcreteHandlers.TouchHandler;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;

public class HelloAndroidActivity extends Activity implements View.OnTouchListener {

    private IGame game;
    private View view;
    private TouchHandler touchHandler;


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
        initialize(this);
        game.startGame();
        setContentView(view);
        view.setOnTouchListener(this);
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
        this.view = new MainView(context, initializer.getInitializedRenderer());
        this.game = new Game(initializer.getInitializedLevel(), view, this);
        this.touchHandler = new TouchHandler();

        //TODO Idea of steering must be change. Currently levels contains InputHandlers objects
    }


    public boolean onTouch(View view, MotionEvent motionEvent) {

        Log.i("ACTIVITY OnTouch", "Touch at: X: " + motionEvent.getX() + " Y: " + motionEvent.getY());
        PointF input = new PointF(motionEvent.getX(), motionEvent.getY());


        game.movePlayer(touchHandler.convertInputToMovement(input, game.getPlayer()));

        return false;
    }
}

