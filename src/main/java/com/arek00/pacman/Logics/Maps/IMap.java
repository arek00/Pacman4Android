package com.arek00.pacman.Logics.Maps;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Logics.Fields.IField;

/**
 * Abstraction for map in game.
 */
public interface IMap {
    public int getField(int x, int y);

    public int[][] getMatrix();

    public int getMapHeight();

    public int getMapWidth();

    public Point getSize();

    public PointF[] getPlayerStartPosition();

    public PointF[] getEnemiesStartPosition();
}
