package com.arek00.pacman.Graphics.Renderers.Utils;

import android.graphics.Point;
import com.arek00.pacman.Config.GraphicsConfig;
import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * Estimates area of map to render by map's offsets and screen resolution.
 */
public class OcclusionEstimator {

    /**
     * Generate occlusion that stores information about starting and endign point of rendering map in chosen dimension.
     *
     * @param offset              Offset of screen shift in given dimension
     * @param dimensionResolution Resolution of screen in given dimension
     * @param maximumValue        Maximum parameter of occlusion should be maximum map's field index
     * @return
     */
    public static Occlusion getOcclusion(float offset, int dimensionResolution, int maximumValue) {
        int startPoint = (int) (-offset / GraphicsConfig.getTileSize()) + 1;
        startPoint = (startPoint > 0) ? startPoint : 0;

        int endPoint = (int) (dimensionResolution / GraphicsConfig.getTileSize() / GraphicsConfig.getMapScale() + startPoint);
        endPoint = (endPoint > maximumValue) ? maximumValue : endPoint;

        return new Occlusion(startPoint, endPoint);
    }

}
