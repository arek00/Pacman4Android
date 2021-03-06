package com.arek00.pacman.Utils.DataHelpers;

import com.arek00.pacman.Activities.Listeners.OnTickListener;

/**
 * Helps with time in game
 */
public class TimeHelper implements OnTickListener {

    private static long lastFrameTime = System.currentTimeMillis();

    static {
        tick();
    }

    /**
     * Get time between rendering frames.
     *
     * @return time from rendering last frame to current in seconds.
     */
    public static float getDeltaTime() {
        return (System.currentTimeMillis() - lastFrameTime) / 1000f;
    }

    /**
     * This method should be used with rendering to refresh the clock and delta counter.
     */
    public static void tick() {
        lastFrameTime = System.currentTimeMillis();
    }

    public void onTick() {
        TimeHelper.tick();
    }
}
