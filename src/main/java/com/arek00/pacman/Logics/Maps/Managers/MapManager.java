package com.arek00.pacman.Logics.Maps.Managers;

import android.content.Context;
import android.content.res.Resources;
import com.arek00.pacman.R;

/**
 */
public class MapManager {

    private String[] mapSchemesPath;

    public MapManager(Context context) {
        this.mapSchemesPath = context.getResources().getStringArray(R.array.mapSchemes);
    }

    /**
     * Get path to map scheme image. Map schemes should be registered in res/values/levels.xml
     *
     * @param id
     * @return
     */
    public String getMapSchemePathById(int id) {

        String mapSchemePath;

        try {
            mapSchemePath = mapSchemesPath[id];
        } catch (IndexOutOfBoundsException exception) {
            exception.printStackTrace();
            String errorInfo = String.format("Given argument ID = %d is wrong.", id);
            throw new IllegalArgumentException(errorInfo);
        }
        return mapSchemePath;
    }

    public int getMapsNumber() {
        return mapSchemesPath.length;
    }

}
