package com.allen.sankeyview.model;

import android.graphics.Path;

import com.allen.sankeyview.view.Point;

/**
 * Sankey diagram node entity class
 *
 * @author Renjy
 */
public class Node {
    /**
     * name : Total
     */

    private String name;
    //The level of the node The root node is 0
    private int nodeLevel;
    //Whether it is a tail node
    private boolean isLastNode = false;
    //the value of the current node
    private float value;
    //Node start point
    private Point startPoint;
    //node end point
    private Point endPoint;
    //node path
    private Path nodePath;
    //the value the node has entered
    private float inputValueSum;
    //The value the node has output
    private float outputValueSum;
    //the height of the node
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
