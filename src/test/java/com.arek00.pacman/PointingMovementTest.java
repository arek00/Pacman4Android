package com.arek00.pacman.Logics.Characters.MovementStrategies.PlayerMovement;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.PlayerCharacter;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class PointingMovementTest {

    private static PointF playerPosition;
    private static int life = 10;
    private static IMovementStrategy movementStrategy;
    private static ICharacter player;

    @org.junit.BeforeClass
    public static void prepareEnv() throws Exception {
        movementStrategy = new PointingMovement(3, 64);
        playerPosition = new PointF(7f, 6.2f);
        player = new PlayerCharacter(playerPosition, life, movementStrategy);

        ShadowLog.stream = System.out;
    }

    @org.junit.Test
    public void touchedPointCoordinatesConversionTest() throws Exception {
        PointF touchAt = new PointF(88.2f, 247.14f);
        player.move(touchAt);

        System.out.println("X: " + touchAt.x + " Y:" + touchAt.y);
    }

    @org.junit.Test
    public void shouldGetToDestinationPoint() throws Exception {
        float startingX = 1f, startingY = 1f;
        float destinationX = 278f, destinationY = 224f;

        PointF startingPosition = new PointF(startingX, startingY);
        PointF destination = new PointF(destinationX, destinationY);

        player.setPosition(startingPosition);

        while (!player.getPosition().equals(destinationX, destinationY)) {
            TimeHelper.tick();

            System.out.println("X: " + startingPosition.x + " Y: " + startingPosition.y);
            PointF dest = new PointF(destinationX, destinationY);
            player.move(dest);
            System.out.println("Destination: " + dest.x + " " + dest.y);

        }
    }
}