package com.arek00.pacman.Config;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Config of accelerometer
 */
public class AccelerometerConfig {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    public static final int Z_AXIS = 2;

    public static final String CALIBRATION_SETTINGS_FILE_NAME = "CalibrationSettings";
    private static final String OFFSET_X_KEY = "offsetX";
    private static final String OFFSET_Y_KEY = "offsetY";
    private static final String PLAYER_AXIS_X_KEY = "playerAxisX";
    private static final String PLAYER_AXIS_Y_KEY = "playerAxisY";
    private static final String FLIP_AXIS_X_KEY = "flipAxisX";
    private static final String FLIP_AXIS_Y_KEY = "flipAxisY";


    private static PointF offset;
    private static int playerXAxis;
    private static int playerYAxis;
    private static boolean flipPlayerXAxis;
    private static boolean flipPlayerYAxis;


    /**
     */
    static {
        setDefaultSettings();
    }


    public static void setDefaultSettings() {
        playerXAxis = X_AXIS;
        playerYAxis = Y_AXIS;
        flipPlayerXAxis = false;
        flipPlayerYAxis = false;
        offset = new PointF(0, 0);
    }

    public static void calibrate(PointF calibrationOffset) {
        offset.x += calibrationOffset.x * getAxisFlip().x;
        offset.y += calibrationOffset.y * getAxisFlip().y;
    }

    public static void setAxis(int xAxis, int yAxis) {
        validateAxis(xAxis);
        validateAxis(yAxis);

        playerXAxis = xAxis;
        playerYAxis = yAxis;
    }

    public static void setFlipX(boolean isFlip) {
        flipPlayerXAxis = isFlip;
    }

    public static void setFlipY(boolean isFlip) {
        flipPlayerYAxis = isFlip;
    }

    private static void validateAxis(int axis) {
        if (axis < 0 || axis > 2) {
            throw new IllegalArgumentException("Chosen axis is incorrect");
        }
    }

    public static PointF getCalibratedOffset() {
        return offset;
    }

    public static Point getPlayerAxis() {
        return new Point(playerXAxis, playerYAxis);
    }

    public static Point getAxisFlip() {
        int flipX = (flipPlayerXAxis) ? -1 : 1;
        int flipY = (flipPlayerYAxis) ? -1 : 1;

        return new Point(flipX, flipY);
    }

    /**
     * @param axisName
     * @return 0 - x, 1 - y, 2 - z, -1 - otherwise
     */
    public static int getAxisIdFromString(String axisName) {
        String text = axisName.toLowerCase();

        if (text.contains("x")) {
            return AccelerometerConfig.X_AXIS;
        } else if (text.contains("y")) {
            return AccelerometerConfig.Y_AXIS;
        } else if (text.contains("z")) {
            return AccelerometerConfig.Z_AXIS;
        } else {
            return -1;
        }
    }

    public static void saveSettings(SharedPreferences.Editor preferencesEditor) {
        preferencesEditor.putFloat(OFFSET_X_KEY, offset.x);
        preferencesEditor.putFloat(OFFSET_Y_KEY, offset.y);
        preferencesEditor.putInt(PLAYER_AXIS_X_KEY, playerXAxis);
        preferencesEditor.putInt(PLAYER_AXIS_Y_KEY, playerYAxis);
        preferencesEditor.putBoolean(FLIP_AXIS_X_KEY, flipPlayerXAxis);
        preferencesEditor.putBoolean(FLIP_AXIS_Y_KEY, flipPlayerYAxis);

        preferencesEditor.commit();
    }

    public static void loadSettings(SharedPreferences preferences) {
        float offsetX = preferences.getFloat(OFFSET_X_KEY, 0f);
        float offsetY = preferences.getFloat(OFFSET_Y_KEY, 0f);
        offset = new PointF(offsetX, offsetY);

        playerXAxis = preferences.getInt(PLAYER_AXIS_X_KEY, X_AXIS);
        playerYAxis = preferences.getInt(PLAYER_AXIS_Y_KEY, Y_AXIS);

        flipPlayerXAxis = preferences.getBoolean(FLIP_AXIS_X_KEY, false);
        flipPlayerYAxis = preferences.getBoolean(FLIP_AXIS_Y_KEY, false);
    }
}
