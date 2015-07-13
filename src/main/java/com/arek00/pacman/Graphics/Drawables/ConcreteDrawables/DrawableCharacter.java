package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * Concrete character with drawable.
 */
public class DrawableCharacter implements Drawable, ICharacter {

    private ICharacter character;
    private Drawable drawable;

    public DrawableCharacter(ICharacter character, Drawable drawable) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(drawable);

        this.character = character;
        this.drawable = drawable;
    }

    public void draw(Canvas canvas) {
        NullPointerValidator.validate(canvas);

        drawable.draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        NullPointerValidator.validate(canvas);
        float tileSize = GraphicsConfig.getTileSize();

        float drawX = x + tileSize/2;
        float drawY = y + tileSize/2;

        drawable.draw(canvas, drawX, drawY);
    }

    public void setPosition(PointF position) {
        NullPointerValidator.validate(position);

        character.setPosition(position);
    }

    public PointF getPosition() {
        return character.getPosition();
    }

    public void move(PointF movement) {
        NullPointerValidator.validate(movement);

        character.move(movement);
    }

    public float getSpeed() {
        return character.getSpeed();
    }

    public void setSpeed(float speed) {
        NumberValidator.checkNegativeNumber(speed);
        NumberValidator.checkNumberIsZero(speed);

        character.setSpeed(speed);
    }
}
