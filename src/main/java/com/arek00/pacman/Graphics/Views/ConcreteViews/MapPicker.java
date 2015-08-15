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
public class MapPicker extends NumberPicker implements NumberPicker.OnValueChangeListener {

    private MapManager mapsManager;
    private Bitmap currentMapBitmap;
    private Context context;


    public MapPicker(Context context, AttributeSet sets) {
        super(context, sets);

        setWillNotDraw(false);
        this.context = context;
        this.mapsManager = new MapManager(context);
        initializePicker(mapsManager);
        setOnValueChangedListener(this);
        loadBitmap(getValue());
    }

    private void initializePicker(MapManager manager) {
        setMinValue(0);
        setMaxValue(mapsManager.getMapsNumber() - 1);
        setOrientation(NumberPicker.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void loadBitmap(int currentValue) {
        String path = this.mapsManager.getMapSchemePathById(currentValue);
        AssetsHelper helper = new AssetsHelper(this.context);
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.currentMapBitmap = BitmapFactory.decodeStream(bitmapStream);
    }

    public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
        loadBitmap(newValue);
    }
}
