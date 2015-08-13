package com.arek00.pacman.Config;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Config of accelerometer
 */
public class AccelerometerConfig {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    public static final int Z_AXIS = 2;

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
}
