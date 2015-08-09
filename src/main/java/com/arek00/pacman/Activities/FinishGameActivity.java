package com.arek00.pacman.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.arek00.pacman.R;

/**
 *
 */
public class FinishGameActivity extends Activity {

    private int points, lives;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_game);
        Intent intent = getIntent();
        setLabels(intent);

    }


    private void setLabels(Intent intent)
    {
        this.points = intent.getIntExtra(GameActivity.POINTS_MESSAGE, 0);
        this.lives = intent.getIntExtra(GameActivity.LIVES_MESSAGE, 0);

        TextView pointsLabel = (TextView)findViewById(R.id.pointsAmount);
        TextView livesLabel = (TextView)findViewById(R.id.livesAmount);

        pointsLabel.setText(Integer.toString(points));
        livesLabel.setText(Integer.toString(lives));
    }

    public void onConfirmButtonClick(View view)
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
