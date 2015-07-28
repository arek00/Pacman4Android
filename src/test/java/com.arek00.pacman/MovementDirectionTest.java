package com.arek00.pacman;

import com.arek00.pacman.Logics.Characters.MovementDirection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Test MovementDirection unit
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class MovementDirectionTest {

    @Test
    public void testGettingDirectionByValue() {
        MovementDirection up = MovementDirection.getDirectionByValue(MovementDirection.UP.value);
        MovementDirection down = MovementDirection.getDirectionByValue(MovementDirection.DOWN.value);
        MovementDirection left = MovementDirection.getDirectionByValue(MovementDirection.LEFT.value);
        MovementDirection none = MovementDirection.getDirectionByValue(MovementDirection.NONE.value);


        assertTrue(up == MovementDirection.UP);
        assertTrue(down == MovementDirection.DOWN);
        assertTrue(left == MovementDirection.LEFT);
        assertTrue(none == MovementDirection.NONE);

    }


}