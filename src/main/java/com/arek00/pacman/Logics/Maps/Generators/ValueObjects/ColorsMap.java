package com.arek00.pacman.Logics.Maps.Generators.ValueObjects;

import android.graphics.Color;

/**
 * Value object.
 * Object that stores information about fields of map and pairing values with colors on bitmap of map scheme.
 * Color is a key of map and value is simple integer.
 * <p/>
 * Values:
 * Color.BLACK - 0,
 * Color.WHITE - 1,
 * Color.BLUE - 2,
 * Color.YELLOW - 3,
 * Color.GREEN - 4,
 * Color.RED - 5,
 */
public class ColorsMap {

    private static int[] colors;
    private final static int COLOR_UNKNOWN = -1;

    static {
        colors = new int[]{Color.rgb(0, 0, 0), //Wall
                Color.rgb(255, 255, 255), //Collected
                Color.rgb(0, 0, 255), //SmallBall
                Color.rgb(0, 255, 255), //big ball
                Color.rgb(0, 255, 0), //Player spawn
                Color.rgb(255, 0, 0) //enemy Spawn
        };
    }

    /**
     * Get field value from given color.
     *
     * @param color
     * @return
     */
    public static int getValue(int color) {
        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
            if (colors[colorIndex] == color) {
                return colorIndex;
            }
        }
        return COLOR_UNKNOWN;
    }

    public static int getFieldsKindsCount() {
        return colors.length;
    }
}
