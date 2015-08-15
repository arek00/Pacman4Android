package com.arek00.pacman.Utils.DataHelpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Admin on 2015-08-15.
 */
public class BitmapRetriever {

    private Context context;

    public BitmapRetriever(Context context) {
        this.context = context;
    }

    public Bitmap retrieveBitmapFromAssets(String bitmapPath) {
        AssetsHelper helper = new AssetsHelper(context);
        InputStream bitmapStream = null;

        try {
            bitmapStream = helper.getFileByName(bitmapPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        NullPointerValidator.validate(bitmapStream);

        return BitmapFactory.decodeStream(bitmapStream);
    }

}
