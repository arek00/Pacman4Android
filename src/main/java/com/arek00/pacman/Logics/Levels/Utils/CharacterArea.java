package com.arek00.pacman.Logics.Levels.Utils;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;

/**
 * Value object of fields that character stays on.
 */
public class CharacterArea {
    private int minX, maxX, minY, maxY;

    public CharacterArea(ICharacter character) {
        PointF characterPosition = character.getPosition();

        this.minX = (int) Math.floor(characterPosition.x);
        this.maxX = (int) Math.ceil(characterPosition.x);
        this.minY = (int) Math.floor(characterPosition.y);
        this.maxY = (int) Math.ceil(characterPosition.y);
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }
}
