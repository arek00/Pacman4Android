package com.arek00.pacman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.arek00.pacman.Graphics.Views.MainView;
import com.arek00.pacman.Initializers.NormalLevelInitializer;
import com.arek00.pacman.Inputs.ConcreteHandlers.TouchHandler;
import com.arek00.pacman.Logics.Game.Game;
import com.arek00.pacman.Logics.Game.IGame;

public class HelloAndroidActivity extends Activity {

    private IGame game;
    private View view;
    private TouchHandler handler;


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
        setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.arek00.pacman.R.menu.main, menu);
        return true;
    }


    /**
     * Initialize all needed objects
     *
     * @param context
     */
    private void initialize(Context context) {
        this.handler = new TouchHandler();
        NormalLevelInitializer initializer = new NormalLevelInitializer(context, handler);
        this.game = new Game(initializer.getInitializedLevel());
        this.view = new MainView(context, handler, initializer.getRenderer());
    }
}

