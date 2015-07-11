package com.arek00.pacman;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Logics.Maps.Utils.FieldsRetriever;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Test of retrieving fields
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class FieldsRetrieverTest {

    private static int[][] values;
    private static Point valuesSize;

    @BeforeClass
    public static void initializeTest() {
        values = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 2, 1, 1, 1},
                {0, 0, 0, 1, 1}
        };
        valuesSize = new Point(5, 5);
    }

    @Test
    public void shouldRetrievesValue2Amount() {
        assertTrue(FieldsRetriever.getCountOfConcreteField(values, 2, valuesSize) == 1);
    }

    @Test
    public void shouldRetrievesAll0Values() {
        ShadowLog.stream = System.out;

        PointF[] points = FieldsRetriever.getPositionsOfConcreteFields(values, 0, valuesSize);

        assertTrue(points.length == 6);

        for (PointF point : points) {
            System.out.println(point.x + " " + point.y);
        }

    }
}
