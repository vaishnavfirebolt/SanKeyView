package com.allen.sankeyview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Node-level entity class
 *
 * @author Renjy
 */
public class Level {
    //Hierarchy
    private int level;
    //collection of all nodes in the current level
    private List<Node> nodes;
    //Number of all nodes in the current level
    private int num;
    //sum of all current node values
    private float valueSum;
    //The sum of the current value of all nodes and the interval (the interval is one percent)
    private float valueAndPaddingSum;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Node> getNodes() {
        if (null == nodes) {
            nodes = new ArrayList<>();
        }
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getValueSum() {
        return valueSum;
    }

    public void setValueSum(float valueSum) {
        this.valueSum = valueSum;
    }

    public float getValueAndPaddingSum() {
        return valueAndPaddingSum;
    }

    public void setValueAndPaddingSum(float valueAndPaddingSum) {
        this.valueAndPaddingSum = valueAndPaddingSum;
    }

    /**
     * Determine whether it is the current level by the level of the hierarchy
     * @param level level
     * @return true is the same level false is not the same level
     */
    public boolean equals(int level) {
        return this.level == level;
    }
}
