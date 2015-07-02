package com.arek00.pacman.Graphics.Renderers;

import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.util.List;

/**
 * Main renderer of part of the application
 */
public abstract class Renderer implements Drawable {

    private List<Drawable> drawableList;

    public Renderer(List<Drawable> drawables) {
        NullPointerValidator.validate(drawables);

        this.drawableList = drawables;
    }

    /**
     * Add drawable to renderer
     *
     * @param item
     */
    public void addDrawableItem(Drawable item) {
        NullPointerValidator.validate(item);

        drawableList.add(item);
    }

    public List<Drawable> getDrawables() {
        return drawableList;
    }

    public void removeDrawable(Drawable drawable) {
        drawableList.remove(drawable);
    }
}