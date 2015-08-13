package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.arek00.pacman.Config.AccelerometerConfig;
import com.arek00.pacman.Graphics.Views.ConcreteViews.CalibrationView;
import com.arek00.pacman.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CalibrationActivity extends Activity {

    private List<RadioButton> xAxisRadioButtons;
    private List<RadioButton> yAxisRadioButtons;
    private CalibrationView calibrationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calibration);
        loadSettings();
        setCalibrationView();
        setValues();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveSettings();
    }


    private void setCalibrationView() {
        this.calibrationView = new CalibrationView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.calibrationLayout);
        frameLayout.addView(calibrationView);
    }

    private void setValues() {
        setOffsets();
        setPlayerAxis();
        setFlipToggles();
    }

    private void setOffsets() {
        TextView offsetXView = (TextView) findViewById(R.id.offsetXResult);
        TextView offsetYView = (TextView) findViewById(R.id.offsetYResult);

        PointF offset = AccelerometerConfig.getCalibratedOffset();

        offsetXView.setText(Float.toString(offset.x));
        offsetYView.setText(Float.toString(offset.y));
    }

    private void setPlayerAxis() {
        putRadioButtonsToList();

        RadioGroup xAxisRadioGroup = (RadioGroup) findViewById(R.id.xAxleRadioGroup);
        RadioGroup yAxisRadioGroup = (RadioGroup) findViewById(R.id.yAxleRadioGroup);

        Point playerAxis = AccelerometerConfig.getPlayerAxis();

        xAxisRadioGroup.check(getRadioViewIdByAxisId(xAxisRadioButtons, playerAxis.x));
        yAxisRadioGroup.check(getRadioViewIdByAxisId(yAxisRadioButtons, playerAxis.y));
    }

    private void putRadioButtonsToList() {
        putXRadioButtonsToList();
        putYRadioButtonsToList();
    }

    private void putXRadioButtonsToList() {
        xAxisRadioButtons = new ArrayList<RadioButton>();
        xAxisRadioButtons.add(0, (RadioButton) findViewById(R.id.xToXRadio));
        xAxisRadioButtons.add(1, (RadioButton) findViewById(R.id.xToYRadio));
        xAxisRadioButtons.add(2, (RadioButton) findViewById(R.id.xToZRadio));
    }

    private void putYRadioButtonsToList() {
        yAxisRadioButtons = new ArrayList<RadioButton>();
        yAxisRadioButtons.add(0, (RadioButton) findViewById(R.id.yToXRadio));
        yAxisRadioButtons.add(1, (RadioButton) findViewById(R.id.yToYRadio));
        yAxisRadioButtons.add(2, (RadioButton) findViewById(R.id.yToZRadio));
    }


    private int getRadioViewIdByAxisId(List<RadioButton> radiosList, int axisId) {
        return radiosList.get(axisId).getId();
    }

    private void setFlipToggles() {
        ToggleButton xAxisToggle = (ToggleButton) findViewById(R.id.flipXToggle);
        ToggleButton yAxisToggle = (ToggleButton) findViewById(R.id.flipYToggle);
        Point flipState = AccelerometerConfig.getAxisFlip();

        xAxisToggle.setChecked(flipState.x < 0);
        yAxisToggle.setChecked(flipState.y < 0);

    }

    public void onCalibrateButtonClick(View view) {
        PointF currentState = calibrationView.getCurrentState();
        PointF calibrationOffsets = new PointF(-currentState.x, -currentState.y);
        AccelerometerConfig.calibrate(calibrationOffsets);
        setOffsets();
    }

    public void onRadioButtonClick(View view) {
        RadioGroup axisXRadioGroup = (RadioGroup) findViewById(R.id.xAxleRadioGroup);
        RadioButton xButton = (RadioButton) findViewById(axisXRadioGroup.getCheckedRadioButtonId());

        RadioGroup axisYRadioGroup = (RadioGroup) findViewById(R.id.yAxleRadioGroup);
        RadioButton yButton = (RadioButton) findViewById(axisYRadioGroup.getCheckedRadioButtonId());


        int xAxis = AccelerometerConfig.getAxisIdFromString(xButton.getText().toString());
        int yAxis = AccelerometerConfig.getAxisIdFromString(yButton.getText().toString());

        AccelerometerConfig.setAxis(xAxis, yAxis);
    }

    public void onFlipToggleClick(View view) {
        ToggleButton flipXToggle = (ToggleButton) findViewById(R.id.flipXToggle);
        ToggleButton flipYToggle = (ToggleButton) findViewById(R.id.flipYToggle);

        AccelerometerConfig.setFlipX(flipXToggle.isChecked());
        AccelerometerConfig.setFlipY(flipYToggle.isChecked());
    }

    public void onReturnButtonClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void onRestoreDefaultSettingsClic(View view) {
        AccelerometerConfig.setDefaultSettings();
        setValues();
    }


    private void saveSettings() {
        SharedPreferences settings = getSharedPreferences(AccelerometerConfig.CALIBRATION_SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor = settings.edit();
        AccelerometerConfig.saveSettings(settingsEditor);
    }

    private void loadSettings() {
        SharedPreferences settings = getSharedPreferences(AccelerometerConfig.CALIBRATION_SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        AccelerometerConfig.loadSettings(settings);
    }

}
