package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.arek00.pacman.Graphics.Views.ConcreteViews.MapPicker;
import com.arek00.pacman.Logics.Maps.Managers.MapsManager;
import com.arek00.pacman.R;

/**
 * Activity where player can customize a game before start like number of enemies, map, etc.
 */
public class SelectCustomGameActivity extends Activity {

    private final int MAX_ENEMIES_NUMBER = 16;
    private final int MAX_LIVES = 99;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game_menu);
        initializePickers();
    }


    public void onStartGame(View view) {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void onReturn(View view) {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void initializePickers() {
        NumberPicker enemiesNumberPicker = (NumberPicker) findViewById(R.id.enemiesNumberPicker);
        NumberPicker livesNumberPicker = (NumberPicker) findViewById(R.id.playerLivesPicker);

        setPickerRange(enemiesNumberPicker, 0, MAX_ENEMIES_NUMBER);
        setPickerRange(livesNumberPicker, 0, MAX_LIVES);
    }

    private void setPickerRange(NumberPicker picker, int minValue, int maxValue) {
        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue);
    }

}
