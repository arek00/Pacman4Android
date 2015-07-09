package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;

/**
 * Concrete character with drawable.
 */
public class CharacterDrawable implements Drawable, ICharacter {

    private ICharacter character;
    private Drawable drawable;

    public CharacterDrawable(ICharacter character, Drawable drawable) {
        this.character = character;
        this.drawable = drawable;
    }

    public void draw(Canvas canvas) {
        drawable.draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        drawable.draw(canvas, x, y);
    }

    public void setPosition(PointF position) {
        character.setPosition(position);
    }

    public PointF getPosition() {
        return character.getPosition();
    }

    public void move(PointF movement) {
        character.move(movement);
    }

    public void setMovementStrategy(IMovementStrategy strategy) {
        character.setMovementStrategy(strategy);
    }
}