package com.allen.sankeyview.model;

import android.graphics.Path;

import com.allen.sankeyview.view.Point;

/**
 * 各个节点之间连接线的实体类
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
    //资源节点的开始位置
    private Point sourceStartPoint;
    //资源节点的结束位置
    private Point sourceEndPoint;
    //目标节点的开始位置
    private Point targetStartPoint;
    //目标节点的结束位置
    private Point targetEndPoint;
    //连接线的路径
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
