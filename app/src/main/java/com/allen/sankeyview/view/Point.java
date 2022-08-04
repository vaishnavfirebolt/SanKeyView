package com.allen.sankeyview.view;

/**
 * Point object entity class on the screen
 * The original Point object x, y is of type int, and the new one is of type Float
 *
 * @author Renjy
 */
public class Point {
    public float x;
    public float y;

    public Point() {
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point src) {
        this.x = src.x;
        this.y = src.y;
    }

    /**
     * Set the point's x and y coordinates
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
