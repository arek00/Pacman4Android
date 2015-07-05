package com.arek00.pacman.Logics.Fields;

import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Maps.ConcreteMap.ImageMapFields;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all fields info and gives all classes that need to render map.
 */
public class MapFieldsManager {

    private static MapFieldsManager instance = new MapFieldsManager();

    private List<Drawable> drawables = new ArrayList<Drawable>();

    private MapFieldsManager() {
    }

    /**
     * Get instance of MapFieldsManager
     *
     * @return
     */
    public static MapFieldsManager getInstance() {
        return instance;
    }

    /**
     * Add item to collection of all elements that might be displayed on map in game.
     *
     * @param item
     */
    public void addDrawableItem(int fieldID, Drawable item) {
        drawables.add(fieldID, item);
    }

    public Drawable getDrawableItem(int fieldID) {
        return drawables.get(fieldID);
    }

    public Drawable getDrawableItem(ImageMapFields field) {
        return getDrawableItem(field.getValue());
    }
}
