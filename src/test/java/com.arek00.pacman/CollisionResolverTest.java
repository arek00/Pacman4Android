package com.arek00.pacman;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Utils.DataHelpers.CollisionResolver;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;
import org.apache.tools.ant.taskdefs.Move;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Test collisions resolver
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class CollisionResolverTest {

    /**
     * Map should looks like
     * |--|
     * |P |
     * |  |
     * |__|
     * <p/>
     * Where lines are walls and P is player spawn position.
     */
    private static int[][] fields;
    private static ICharacter character;
    private static final int WALL = FieldsEnum.WALL.field.getValue();
    private static final int SPAWN = FieldsEnum.PLAYER_SPAWN.field.getValue();
    private static final int EMPTY_FIELD = FieldsEnum.COLLECTED.field.getValue();

    /**
     * Prepare test
     */
    @BeforeClass
    public static void initTest() {
        TimeHelper.tick();

        fields = new int[4][4];
        fields[0] = new int[]{WALL, WALL, WALL, WALL};
        fields[1] = new int[]{WALL, SPAWN, EMPTY_FIELD, WALL};
        fields[2] = new int[]{WALL, EMPTY_FIELD, EMPTY_FIELD, WALL};
        fields[3] = new int[]{WALL, WALL, WALL, WALL};

        printMap(fields);

        character = new Player(new PointF(1, 1), 5, 5);
    }


    private static void printMap(int[][] fields) {

        ShadowLog.stream = System.out;

        for (int[] level : fields) {
            for (int field : level) {
                if (field == FieldsEnum.WALL.field.getValue()) {
                    System.out.print("*");
                } else if (field == FieldsEnum.PLAYER_SPAWN.field.getValue()) {
                    System.out.print("P");
                } else if (field == FieldsEnum.COLLECTED.field.getValue()) {
                    System.out.print(" ");
                }
            }
            System.out.print('\n');
        }
    }

    @Test
    public void testCollisionDetection() {
        TimeHelper.tick();

        character.setPosition(1, 1);
        assertFalse(CollisionResolver.isCollide(character, fields));

        character.setPosition(0, 1);
        assertTrue(CollisionResolver.isCollide(character, fields));
    }

    @Test
    public void testCollisionResolving() {
        TimeHelper.tick();

        int testingSteps = 100;

        MovementDirection characterMovementDirection = MovementDirection.LEFT;
        character.setPosition(1, 1);

        for (int i = 0; i < testingSteps; i++) {
            moveAndTestCollision(character, characterMovementDirection);
        }

        assertTrue(character.getPosition().x == 1f);
        assertTrue(character.getPosition().y == 1f);
    }

    private void moveAndTestCollision(ICharacter character, MovementDirection direction) {
        ShadowLog.stream = System.out;
        String log = String.format("Player position x: %.2f, y: %.2f", character.getPosition().x, character.getPosition().y);
        System.out.println(log);

        movementDirectionToMove(character, direction);

        if (CollisionResolver.isCollide(character, fields)) {
            CollisionResolver.resolveCollision(character, direction);
        }
    }

    /**
     * Method to testing
     *
     * @param character
     * @param direction
     */
    private void movementDirectionToMove(ICharacter character, MovementDirection direction) {
        float x = character.getPosition().x;
        float y = character.getPosition().y;

        if (direction == MovementDirection.LEFT) {
            character.setPosition(--x, y);
        } else if (direction == MovementDirection.RIGHT) {
            character.setPosition(++x, y);
        } else if (direction == MovementDirection.UP) {
            character.setPosition(x, --y);
        } else if (direction == MovementDirection.DOWN) {
            character.setPosition(x, ++y);
        }
    }

}