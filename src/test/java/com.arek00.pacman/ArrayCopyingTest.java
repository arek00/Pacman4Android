package com.arek00.pacman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;

/**
 * Behavioural test of copying arrays.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class ArrayCopyingTest {

    private int[] testArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    public void copyingTest() {

        int[] clonedArray = testArray.clone();

        for (int i = 0; i < clonedArray.length; i++) {
            ++clonedArray[i];

            assertFalse(clonedArray[i] == testArray[i]);
        }
    }
}
