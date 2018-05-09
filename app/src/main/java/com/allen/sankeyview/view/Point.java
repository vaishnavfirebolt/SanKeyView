package com.allen.sankeyview.view;

/**
 * 屏幕上点对象实体类
 * 原Point对象x,y为int类型  新建的为Float类型
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
