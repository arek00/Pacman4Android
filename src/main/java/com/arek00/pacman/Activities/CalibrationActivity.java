package com.arek00.pacman.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.arek00.pacman.Graphics.Views.ConcreteViews.CalibrationView;
import com.arek00.pacman.R;

/**
 *
 */
public class CalibrationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibration);
        View calibrationView = new CalibrationView(this);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.calibrationLayout);
        frameLayout.addView(calibrationView);
    }
}
