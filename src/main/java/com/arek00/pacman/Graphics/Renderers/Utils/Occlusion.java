package com.arek00.pacman.Graphics.Renderers.Utils;

/**
 * Stores information about starting and ending map rendering point in one of dimension.
 */
public class Occlusion {

    private int startPosition;
    private int endPosition;

    public Occlusion(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

}
