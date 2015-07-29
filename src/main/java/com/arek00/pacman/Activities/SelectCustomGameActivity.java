package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arek00.pacman.R;

/**
 * Activity where player can customize a game before start like number of enemies, map, etc.
 */
public class SelectCustomGameActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game_menu);
    }

    public void onStartGame(View view)
    {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void onReturn(View view)
    {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
