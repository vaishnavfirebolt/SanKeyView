package com.allen.sankeyview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点层级实体类
 *
 * @author Renjy
 */
public class Level {
    //层级
    private int level;
    //当前层级所有节点集合
    private List<Node> nodes;
    //当前层级所有节点个数
    private int num;
    //当前所有节点数值之和
    private float valueSum;
    //当前所有节点数值和间隔之和（间隔为百分之一）
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
     * 通过层级级别来判断是否是当前等级
     * @param level 级别
     * @return true 是同一层级 false 不是同一个层级
     */
    public boolean equals(int level) {
        return this.level == level;
    }
}
