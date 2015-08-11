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
    private static int chosenXAxis;
    private static int chosenYAxis;
    private static boolean flipChosenXAxis;
    private static boolean flipChosenYAxis;


    /**
     */
    static {
        setDefaultSettings();
    }


    private static void setDefaultSettings() {
        chosenXAxis = X_AXIS;
        chosenYAxis = Y_AXIS;
        flipChosenXAxis = false;
        flipChosenYAxis = false;
    }

    public static void calibrate(PointF calibrationOffset) {
        offset.x = calibrationOffset.x;
        offset.y = calibrationOffset.y;
    }

    public static void setAxis(int xAxle, int yAxle) {
        validateAxis(xAxle);
        validateAxis(yAxle);

        chosenXAxis = xAxle;
        chosenYAxis = yAxle;
    }

    public static void setFlipX(boolean isFlip) {
        flipChosenXAxis = isFlip;
    }

    public static void setFlipY(boolean isFlip) {
        flipChosenYAxis = isFlip;
    }

    private static void validateAxis(int axle) {
        if (axle < 0 || axle > 2) {
            throw new IllegalArgumentException("Chosen axle is incorrect");
        }
    }

    public static PointF getCalibratedOffset() {
        return offset;
    }

    public static Point getChosenAxis() {
        return new Point(chosenXAxis, chosenYAxis);
    }

    public static Point getAxisFlip() {
        int flipX = (flipChosenXAxis) ? -1 : 1;
        int flipY = (flipChosenYAxis) ? -1 : 1;

        return new Point(flipX, flipY);
    }
}
