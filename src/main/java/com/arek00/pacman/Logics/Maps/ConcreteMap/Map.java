package com.arek00.pacman.Logics.Maps.ConcreteMap;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Logics.Maps.Utils.FieldsRetriever;


/**
 *
 */
public class Map implements IMap {
    private String name;
    private int[][] fields;
    private Point size;
    private PointF[] playerStartingPositions;
    private PointF[] enemiesStartingPositions;

    public Map(String name, int[][] fields, int width, int height) {
        this.name = name;
        this.fields = fields;
        this.size = new Point(width, height);
        initializeStartingPositions();
    }

    public int getField(int x, int y) {
        return fields[x][y];
    }

    public int[][] getMatrix() {
        return fields.clone();
    }

    public int getMapHeight() {
        return size.y;
    }

    public Point getSize() {
        return new Point(size);
    }

    private void initializeStartingPositions() {
        playerStartingPositions = retrievePlayerStartingPositions();
        enemiesStartingPositions = retrieveEnemiesStartingPositions();
    }

    private PointF[] retrievePlayerStartingPositions() {
        return FieldsRetriever.getPositionsOfConcreteFields(fields, FieldsEnum.PLAYER_SPAWN.field.getValue(), size);
    }

    private PointF[] retrieveEnemiesStartingPositions() {
        return FieldsRetriever.getPositionsOfConcreteFields(fields, FieldsEnum.ENEMY_SPAWN.field.getValue(), size);
    }

    public int getMapWidth() {
        return size.x;
    }

    public PointF[] getPlayerStartPosition() {
        return playerStartingPositions;
    }

    public PointF[] getEnemiesStartPosition() {
        return enemiesStartingPositions;
    }

    public String getMapName() {
        return name;
    }
}
