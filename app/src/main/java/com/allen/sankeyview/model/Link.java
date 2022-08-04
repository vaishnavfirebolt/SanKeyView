package com.allen.sankeyview.model;

import android.graphics.Path;

import com.allen.sankeyview.view.Point;

/**
 * Entity class for connecting lines between nodes
 *
 * @author Renjy
 */
public class Link {
    /**
     * source : Total
     * target : Environment
     * value : 0.342284047256003
     */

    private String source;
    private String target;
    private float value;
    //The starting position of the resource node
    private Point sourceStartPoint;
    //The end position of the resource node
    private Point sourceEndPoint;
    //the starting position of the target node
    private Point targetStartPoint;
    //the end position of the target node
    private Point targetEndPoint;
    //the path of the connecting line
    private Path linkLinePath;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {

        this.value = value;
    }

    public Point getSourceStartPoint() {
        return sourceStartPoint;
    }

    public void setSourceStartPoint(Point sourceStartPoint) {
        this.sourceStartPoint = sourceStartPoint;
    }

    public Point getSourceEndPoint() {
        return sourceEndPoint;
    }

    public void setSourceEndPoint(Point sourceEndPoint) {
        this.sourceEndPoint = sourceEndPoint;
    }

    public Point getTargetStartPoint() {
        return targetStartPoint;
    }

    public void setTargetStartPoint(Point targetStartPoint) {
        this.targetStartPoint = targetStartPoint;
    }

    public Point getTargetEndPoint() {
        return targetEndPoint;
    }

    public void setTargetEndPoint(Point targetEndPoint) {
        this.targetEndPoint = targetEndPoint;
    }

    public Path getLinkLinePath() {
        return linkLinePath;
    }

    public void setLinkLinePath(Path linkLinePath) {
        this.linkLinePath = linkLinePath;
    }
}
