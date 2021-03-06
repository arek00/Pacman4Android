package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.arek00.pacman.Activities.ConcreteKeyListeners.BackOnReturn;
import com.arek00.pacman.Activities.ConcreteKeyListeners.OnReturnKeyStrategy;
import com.arek00.pacman.R;

/**
 * Activity where player can customize a game before start like number of enemies, map, etc.
 */
public class SelectCustomGameActivity extends Activity {

    private final int MAX_ENEMIES_NUMBER = 16;
    private final int MAX_LIVES = 99;

    public static final String LEVEL_ID_MESSAGE = "com.arek00.Activities.levelId";
    public static final String STARTING_LIVES_MESSAGE = "com.arek00.Activities.startingLives";
    public static final String STARTING_ENEMIES_MESSAGE = "com.arek00.Activities.startingEnemies";

    private OnReturnKeyStrategy onReturnKeyStrategy;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game_menu);
        initializePickers();

        onReturnKeyStrategy = new BackOnReturn(this, MenuActivity.class);
    }

    public void onStartGame(View view) {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, GameActivity.class);
        putExtrasToGame(intent);
        startActivity(intent);
        finish();
    }


    private void putExtrasToGame(Intent intent) {
        NumberPicker levelPicker = (NumberPicker) findViewById(R.id.levelIDPicker);
        NumberPicker livesPicker = (NumberPicker) findViewById(R.id.playerLivesPicker);
        NumberPicker enemiesPicker = (NumberPicker) findViewById(R.id.enemiesNumberPicker);

        intent.putExtra(SelectCustomGameActivity.LEVEL_ID_MESSAGE, levelPicker.getValue());
        intent.putExtra(SelectCustomGameActivity.STARTING_ENEMIES_MESSAGE, enemiesPicker.getValue());
        intent.putExtra(SelectCustomGameActivity.STARTING_LIVES_MESSAGE, livesPicker.getValue());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        onReturnKeyStrategy.executeOnReturnKey();
    }
}
