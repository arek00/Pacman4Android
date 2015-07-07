package com.arek00.pacman.Logics.Maps;

/**
 * Abstraction for map in game.
 */
public interface IMap {
    public int getField(int x, int y);
    public int getMapHeight();
    public int getMapWidth();
}
