package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Created by Admin on 2015-08-16.
 */
public class Animation implements Drawable {

    private Bitmap[] bitmapsStreak;
    private float[] frameTimes;
    private int currentFrame = 0;
    private long startingTime;
    private boolean playing = false;
    private boolean repeatable = false;
    private int frames;
    private Paint paint;

    /**
     * Set frames of animation and times that every frame take.
     * Both arrays have to be equal in length.
     *
     * @param bitmapsStreak - sequence of frames
     * @param frameTimes    - time of every frame
     */
    public Animation(Bitmap[] bitmapsStreak, float[] frameTimes) {
        NullPointerValidator.validate(bitmapsStreak);
        NullPointerValidator.validate(frameTimes);

        if (bitmapsStreak.length != frameTimes.length) {
            throw new IllegalArgumentException("BitmapStreak and FrameTimes arrays have to be equal lenghts");
        }

        this.bitmapsStreak = bitmapsStreak;
        this.frameTimes = frameTimes;
        this.frames = bitmapsStreak.length;
        this.paint = new Paint();
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public void playAnimation() {
        if (playing) {
            if (System.currentTimeMillis() - startingTime > frameTimes[currentFrame]) {
                ++currentFrame;

                if (currentFrame == frames) {
                    currentFrame = 0;
                }

                if (!repeatable) {
                    stopAnimation();
                }
            }
        } else {
            currentFrame = 0;
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public void startAnimation() {
        playing = true;
        this.startingTime = System.currentTimeMillis();
    }

    public void stopAnimation() {
        playing = false;
    }

    public void draw(Canvas canvas) {
        playAnimation();
        canvas.drawBitmap(bitmapsStreak[currentFrame], 0, 0, this.paint);
    }

    public void draw(Canvas canvas, float x, float y) {
        playAnimation();
        canvas.drawBitmap(bitmapsStreak[currentFrame], x, y, this.paint);
    }
}
