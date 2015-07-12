package com.arek00.pacman.Utils.DataHelpers;

import android.content.Context;
import android.content.res.AssetManager;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class AssetsHelper {

    private Context context;
    private AssetManager manager;

    public AssetsHelper(Context context) {
        NullPointerValidator.validate(context);

        this.context = context;
        manager = context.getAssets();
    }

    /**
     * Open file from assets directory.
     *
     * @param fileName - file path in assets directory
     * @return input stream to file.
     */
    public InputStream getFileByName(String fileName) throws IOException {
        NullPointerValidator.validate(fileName);

        return manager.open(fileName);
    }

    /**
     * Get helper's context.
     *
     * @return
     */
    public Context getContext() {
        return this.context;
    }
}
