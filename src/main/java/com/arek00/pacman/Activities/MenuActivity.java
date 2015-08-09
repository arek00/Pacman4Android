package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arek00.pacman.R;

/**
 * Main menu activity
 */
public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void onStartGameTouch(View view) {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, SelectCustomGameActivity.class);
        startActivity(intent);
        finish();
    }
}
