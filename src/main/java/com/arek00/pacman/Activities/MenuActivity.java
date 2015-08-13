package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.arek00.pacman.Config.AccelerometerConfig;
import com.arek00.pacman.R;

/**
 * Main menu activity
 */
public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        loadAccelerometerSettings();
    }


    public void onStartGameTouch(View view) {
        Toast toast = Toast.makeText(this, "Start the Game", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, SelectCustomGameActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCalibrationButtonTouch(View view) {
        Intent intent = new Intent(this, CalibrationActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadAccelerometerSettings() {
        SharedPreferences settings = getSharedPreferences(AccelerometerConfig.CALIBRATION_SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        AccelerometerConfig.loadSettings(settings);
    }
}
