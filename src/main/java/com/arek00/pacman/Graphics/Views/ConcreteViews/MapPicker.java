package com.arek00.pacman.Graphics.Views.ConcreteViews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.NumberPicker;
import com.arek00.pacman.Logics.Maps.Managers.MapManager;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;

import java.io.IOException;
import java.io.InputStream;


/**
 *
 */
public class MapPicker extends NumberPicker{

    private MapManager mapsManager;
    private Bitmap currentMapBitmap;
    private Context context;


    public MapPicker(Context context, AttributeSet sets) {
        super(context, sets);

        setWillNotDraw(false);
        this.context = context;
        this.mapsManager = new MapManager(context);
        initializePicker(mapsManager);
    }

    private void initializePicker(MapManager manager) {
        setMinValue(0);
        setMaxValue(mapsManager.getMapsNumber() - 1);
        setOrientation(NumberPicker.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
