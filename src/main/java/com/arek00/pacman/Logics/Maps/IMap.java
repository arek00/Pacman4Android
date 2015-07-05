package com.arek00.pacman.Logics.Maps;

import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;

/**
 * Abstraction for classes that display map in game.
 */
public interface IMap {
    public Drawable getField(int x, int y);
    public int getMapHeight();
    public int getMapWidth();
}
