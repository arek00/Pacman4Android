package com.arek00.pacman.Logics.Maps.ConcreteMap;

import android.graphics.Point;
import com.arek00.pacman.Logics.Maps.IMap;


/**
 *
 */
public class Map implements IMap {
    private String name;
    private int[][] fields;
    private Point size;


    public Map(String name, int[][] fields, int width, int height) {
        this.name = name;
        this.fields = fields;
        this.size = new Point(width, height);
    }

    public int getField(int x, int y) {
        return fields[x][y];
    }

    public int getMapHeight() {
        return size.y;
    }

    public int getMapWidth() {
        return size.x;
    }

    public String getMapName() {
        return name;
    }
}
