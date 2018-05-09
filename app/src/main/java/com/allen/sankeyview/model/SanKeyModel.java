package com.allen.sankeyview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 桑基图的实体类
 *
 * @author Renjy
 */
public class SanKeyModel {

    private List<Node> nodes;
    private List<Link> links;
    //根级节点集合
    private List<Node> rootNodes;
    //节点最大的级别（从1开始 顶级为0）
    private int maxLevel;
    //层级集合
    private List<Level> levelList;
    //层级数值之和的最大值
    private float maxLevelValueSum;
    //层级数值和间隔之和的最大值
    private float maxLevelValueAndPaddingSum;
    //节点之间的间隔
    private float mNodePadding;
    //选中的节点
    private Node selectNode;
    //选中的线
    private Link selectLink;
    //是否拥有绘制所需的值
    private boolean isCalculation;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Node> getRootNodes() {
        return rootNodes;
    }

    public void setRootNodes(List<Node> rootNodes) {
        this.rootNodes = rootNodes;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public List<Level> getLevelList() {
        if (null == levelList) {
            levelList = new ArrayList<>();
        }
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public float getMaxLevelValueSum() {
        return maxLevelValueSum;
    }

    public void setMaxLevelValueSum(float maxLevelValueSum) {
        this.maxLevelValueSum = maxLevelValueSum;
    }

    public float getNodePadding() {
        return mNodePadding;
    }

    public void setNodePadding(float nodePadding) {
        this.mNodePadding = nodePadding;
    }

    public float getMaxLevelValueAndPaddingSum() {
        return maxLevelValueAndPaddingSum;
    }

    public void setMaxLevelValueAndPaddingSum(float maxLevelValueAndPaddingSum) {
        this.maxLevelValueAndPaddingSum = maxLevelValueAndPaddingSum;
    }

    public Node getSelectNode() {
        return selectNode;
    }

    public void setSelectNode(Node selectNode) {
        this.selectNode = selectNode;
    }

    public Link getSelectLink() {
        return selectLink;
    }

    public void setSelectLink(Link selectLink) {
        this.selectLink = selectLink;
    }

    /**
     * 取消已选中的节点和线
     */
    public void cancelSelectNodeAndLink(){
        this.selectNode = null;
        this.selectLink = null;
    }

    public boolean isCalculation() {
        return isCalculation;
    }

    public void setCalculation(boolean calculation) {
        isCalculation = calculation;
    }
}
