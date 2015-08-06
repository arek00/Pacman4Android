package com.arek00.pacman.Utils.DataHelpers;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.Utils.CharacterArea;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 *
 */
public class CollisionResolver {

    /**
     * Check if given character collides with any field in fields array
     *
     * @param character - Character to test
     * @param fields    - array that contains information about fields of map to check
     * @return
     */
    public static boolean isCollide(ICharacter character, int[][] fields) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(fields);

        CharacterArea area = new CharacterArea(character);

        if (FieldsEnum.isFieldCollide(
                fields[area.getMinX()][area.getMinY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[area.getMinX()][area.getMaxY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[area.getMaxX()][area.getMinY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[area.getMaxX()][area.getMaxY()])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if player collides with any of enemies.
     *
     * @param player
     * @param enemies
     * @return
     */
    public static boolean isCollide(ICharacter player, ICharacter[] enemies) {
        for (ICharacter enemy : enemies) {

            if (distanceBetween(enemy.getPosition().x, player.getPosition().x) < 1f &&
                    distanceBetween(enemy.getPosition().y, player.getPosition().y) < 1f) {
                return true;
            }
        }
        return false;
    }

    private static float distanceBetween(float a, float b) {
        return Math.abs((a - b));
    }

    /**
     * Resolve collision of character.
     * Character is being move back by
     *
     * @param character
     * @param direction
     */
    public static void resolveCollision(ICharacter character, MovementDirection direction) {
        float characterX = character.getPosition().x;
        float characterY = character.getPosition().y;

        MovementDirection correctingDirection = MovementDirection.getDirectionByValue(direction.reverse);
        float horizontalVector = MovementEstimator.calculateHorizontalVector(correctingDirection);
        float verticalVector = MovementEstimator.calculateVerticalVector(correctingDirection);

        characterX += horizontalVector;
        characterY += verticalVector;

        if (direction == MovementDirection.DOWN) {
            characterY = (float) Math.ceil(character.getPosition().y);
        } else if (direction == MovementDirection.UP) {
            characterY = (float) Math.floor(character.getPosition().y);
        } else if (direction == MovementDirection.LEFT) {
            characterX = (float) Math.floor(character.getPosition().x);
        } else if (direction == MovementDirection.RIGHT) {
            characterX = (float) Math.ceil(character.getPosition().x);
        }

        character.setPosition(characterX, characterY);
    }

}
