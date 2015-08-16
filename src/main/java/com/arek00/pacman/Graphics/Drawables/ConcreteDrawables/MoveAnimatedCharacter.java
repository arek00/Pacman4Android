package com.arek00.pacman.Graphics.Drawables.ConcreteDrawables;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 */
public class MoveAnimatedCharacter implements ICharacter, Drawable {

    private ICharacter character;
    private Drawable[] drawableTiles;
    private MovementDirection lastMove;

    public MoveAnimatedCharacter(ICharacter character, Drawable noneMoveTile, Drawable upMoveTile, Drawable downMoveTile, Drawable leftMoveTile, Drawable rightMoveTile) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(noneMoveTile);
        NullPointerValidator.validate(upMoveTile);
        NullPointerValidator.validate(downMoveTile);
        NullPointerValidator.validate(leftMoveTile);
        NullPointerValidator.validate(rightMoveTile);

        this.drawableTiles = new Drawable[]{noneMoveTile, upMoveTile, downMoveTile, leftMoveTile, rightMoveTile};
        this.character = character;
        lastMove = MovementDirection.NONE;
    }

    /**
     * @param character
     * @param moveDrawableTiles - Tiles in array should be set in given order: NONE, UP, DOWN, LEFT, RIGHT
     */
    public MoveAnimatedCharacter(ICharacter character, Drawable[] moveDrawableTiles) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(moveDrawableTiles);

        if (moveDrawableTiles.length != 5) {
            throw new IllegalArgumentException("moveDrawableTiles has to contain 5 frames inside");
        }

        this.character = character;
        this.drawableTiles = moveDrawableTiles;
        lastMove = MovementDirection.NONE;
    }


    public void draw(Canvas canvas) {
        drawableTiles[lastMove.value].draw(canvas);
    }

    public void draw(Canvas canvas, float x, float y) {
        drawableTiles[lastMove.value].draw(canvas, x, y);
    }

    public void setPosition(PointF position) {
        character.setPosition(position);
    }

    public void setPosition(float x, float y) {
        character.setPosition(x, y);
    }

    public PointF getPosition() {
        return character.getPosition();
    }

    public void move(MovementDirection direction) {

        this.lastMove = direction;
        character.move(direction);
    }

    public float getSpeed() {
        return character.getSpeed();
    }

    public void setSpeed(float speed) {
        character.setSpeed(speed);
    }

}
