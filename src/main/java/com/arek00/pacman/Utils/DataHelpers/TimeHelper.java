package com.arek00.pacman.Utils.DataHelpers;

import com.arek00.pacman.Activities.Listeners.OnTickListener;

/**
 * Helps with time in game
 */
public class TimeHelper implements OnTickListener {

    private static long lastFrameTime = System.nanoTime();

    static {
        tick();
    }

    /**
     * Get time between rendering frames.
     *
     * @return time from rendering last frame to current in seconds.
     */
    public static float getDeltaTime() {
        return (System.nanoTime() - lastFrameTime) / 1000000000f;
    }

    /**
     * This method should be used with rendering to refresh the clock and delta counter.
     */
    public static void tick() {
        lastFrameTime = System.nanoTime();
    }

    public void onTick() {
        TimeHelper.tick();

        //  Log.i("TIME HELPER INFO", "Last interval between frames: " + TimeHelper.getDeltaTime());
    }
}
