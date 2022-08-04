package com.allen.sankeyview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sankey diagram entity class
 *
 * @author Renjy
 */
public class SanKeyModel {

    private List<Node> nodes;
    private List<Link> links;
    //root-level node collection
    private List<Node> rootNodes;
    //The maximum level of the node (starting from 1, top level is 0)
    private int maxLevel;
    //Hierarchical collection
    private List<Level> levelList;
    //The maximum value of the sum of the level values
    private float maxLevelValueSum;
    //The maximum value of the sum of the level value and the interval
    private float maxLevelValueAndPaddingSum;
    //interval between nodes
    private float mNodePadding;
    //selected node
    private Node selectNode;
    //selected line
    private Link selectLink;
    //Does it have the values needed to draw
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

    public boolean isCalculation() {
        return isCalculation;
    }

    public void setCalculation(boolean calculation) {
        isCalculation = calculation;
    }
}
