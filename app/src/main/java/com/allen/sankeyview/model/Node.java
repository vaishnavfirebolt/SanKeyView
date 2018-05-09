package com.allen.sankeyview.model;

import android.graphics.Path;

import com.allen.sankeyview.view.Point;

/**
 * 桑基图的节点实体类
 *
 * @author Renjy
 */
public class Node {
    /**
     * name : Total
     */

    private String name;
    //节点的级别 根节点为0
    private int nodeLevel;
    //是否为尾节点
    private boolean isLastNode = false;
    //当前节点的值
    private float value;
    //节点开始点
    private Point startPoint;
    //节点结束点
    private Point endPoint;
    //节点path
    private Path nodePath;
    //节点已输入的值
    private float inputValueSum;
    //节点已输出的值
    private float outputValueSum;
    //节点的高度
    private float nodeHeight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(int nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public boolean isLastNode() {
        return isLastNode;
    }

    public void setLastNode(boolean lastNode) {
        isLastNode = lastNode;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Point getStartPoint() {
        if (null == startPoint) {
            startPoint = new Point();
        }
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        if (null == endPoint) {
            endPoint = new Point();
        }
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Path getNodePath() {
        return nodePath;
    }

    public void setNodePath(Path nodePath) {
        this.nodePath = nodePath;
    }

    public float getInputValueSum() {
        return inputValueSum;
    }

    public void setInputValueSum(float inputValueSum) {
        this.inputValueSum = inputValueSum;
    }

    public float getOutputValueSum() {
        return outputValueSum;
    }

    public void setOutputValueSum(float outputValueSum) {
        this.outputValueSum = outputValueSum;
    }

    public float getNodeHeight() {
        return nodeHeight;
    }

    public void setNodeHeight(float nodeHeight) {
        this.nodeHeight = nodeHeight;
    }

}
