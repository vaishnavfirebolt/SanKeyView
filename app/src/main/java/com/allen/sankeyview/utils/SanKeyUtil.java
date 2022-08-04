package com.allen.sankeyview.utils;

import com.allen.sankeyview.model.Level;
import com.allen.sankeyview.model.Link;
import com.allen.sankeyview.model.Node;
import com.allen.sankeyview.model.SanKeyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sankey diagram tools
 *
 * @author Renjy
 */
public class SanKeyUtil {
    /**
     * Calculate the data for the Sankey diagram
     *
     * @param model data
     */
    public static void   calculationSanKeyModel(SanKeyModel model) {
        // get all root nodes
        getRootNodes(model);
        // Calculate all lines and level of each node (from root to tail)
        calculationAllLinks(model);
        //Calculate the value of all nodes (specific gravity value)
        calculationNodesValue(model);
        //Calculate level related data
        calculationLevel(model);
        //Calculate the sum of the values of each level (sum of proportions)
        calculationLevelNodeValue(model);
        //Calculate the sum of the value of each level and the interval (the interval is a percentage of the maximum value of the level)
        calculationLevelNodeAndPaddingSum(model);
    }


    /**
     * Get the root node (top-level node)
     *
     * @param model 桑基图实体类
     */
    private static void getRootNodes(SanKeyModel model) {
        List<Link> links = model.getLinks();
        List<Node> nodes = model.getNodes();
        List<Node> rootNodes = new ArrayList<>(nodes);
        for (Node node : nodes) {
            for (Link link : links) {
                if (link.getTarget().equals(node.getName())) {
                    rootNodes.remove(node);
                    break;
                }
            }
        }
        for (Node node : rootNodes) {
            node.setNodeLevel(0);
        }
        model.setRootNodes(rootNodes);

    }

    /**
     * Calculate all the connecting lines, from the root node to the tail node
     *
     * @param model 数据
     */
    private static void calculationAllLinks(SanKeyModel model) {
        List<Node> rootNodes = model.getRootNodes();
        List<List<Node>> linkLineNodes = new ArrayList<>();
        for (Node node : rootNodes) {
            List<Node> lineNodes = new ArrayList<>();
            lineNodes.add(node);
            getChildNodes(node, model.getLinks(), model.getNodes(), lineNodes, linkLineNodes);

        }
        //设置节点的级别
        for (List<Node> nodeList : linkLineNodes) {
            if (nodeList.size() > model.getMaxLevel()) {
                model.setMaxLevel(nodeList.size());
            }
            for (int i = 0; i < nodeList.size(); i++) {
                Node node = nodeList.get(i);
                if (node.getNodeLevel() < i) {
                    node.setNodeLevel(i);
                }
            }
        }
    }


    /**
     * get next node
     *
     * @param node  数据
     * @param links 连接线
     * @param nodes 所有节点
     */
    private static void getChildNodes(Node node, List<Link> links, List<Node> nodes, List<Node> lineNodes, List<List<Node>> linkLineNodes) {
        List<Node> childNodes = null;
        boolean isLastNode = true;
        for (Link link : links) {
            childNodes = new ArrayList<>();
            if (link.getSource().equals(node.getName())) {
                String name = link.getTarget();
                Node childNode = getNodeFromNodeName(name, nodes);
                childNodes.addAll(lineNodes);
                childNodes.add(childNode);
                getChildNodes(childNode, links, nodes, childNodes, linkLineNodes);
                isLastNode = false;
            }
        }
        if (null != childNodes && isLastNode) {
            node.setLastNode(true);//设置当前节点为尾节点
            childNodes.addAll(lineNodes);
            linkLineNodes.add(childNodes);
        }
    }

    /**
     * Get the corresponding node by the name of the node
     *
     * @param name  节点名称
     * @param nodes 所有节点
     * @return 节点名称对应的节点
     */
    public static Node getNodeFromNodeName(String name, List<Node> nodes) {
        Node targetNode = null;
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                targetNode = node;
                break;
            }
        }
        return targetNode;
    }

    /**
     * Calculate the value of the node
     *
     * @param model 数值
     */
    private static void calculationNodesValue(SanKeyModel model) {
        List<Link> links = model.getLinks();
        List<Node> nodes = model.getNodes();
        for (Link link : links) {
            Node sourceNode = getNodeFromNodeName(link.getSource(), nodes);
            Node targetNode = getNodeFromNodeName(link.getTarget(), nodes);
            float value = link.getValue();
            if (sourceNode.getNodeLevel() == 0) {
                sourceNode.setValue(sourceNode.getValue() + value);
            }
            targetNode.setValue(targetNode.getValue() + value);
        }

    }

    /**
     * Computational level related data
     *
     * @param model 数据
     */
    private static void calculationLevel(SanKeyModel model) {
        List<Node> nodes = model.getNodes();
        List<Level> levelList = model.getLevelList();
        int maxLevel = model.getMaxLevel();
        for (Node node : nodes) {
            addNodeToLevel(node, levelList, maxLevel);
        }
        Collections.sort(levelList, new Comparator<Level>() {
            @Override
            public int compare(Level o1, Level o2) {
                return o1.getLevel() - o2.getLevel();
            }
        });
    }

    /**
     * Add nodes to the hierarchy
     *
     * @param node      节点
     * @param levelList 层级集合
     */
    private static void addNodeToLevel(Node node, List<Level> levelList, int maxLevel) {
        int nodeLevel = node.getNodeLevel();
        //判断是否是尾节点
        Level currentLevel = null;
        if (node.isLastNode()) {
            for (Level level : levelList) {
                if (level.equals(maxLevel - 1)) {
                    currentLevel = level;
                    break;
                }
            }
            if (null == currentLevel) {
                currentLevel = new Level();
                currentLevel.setLevel(maxLevel - 1);
                levelList.add(currentLevel);
            }
            currentLevel.getNodes().add(node);
        } else {
            for (Level level : levelList) {
                if (level.equals(nodeLevel)) {
                    currentLevel = level;
                    break;
                }
            }
            if (null == currentLevel) {
                currentLevel = new Level();
                currentLevel.setLevel(nodeLevel);
                levelList.add(currentLevel);
            }
            currentLevel.getNodes().add(node);
        }

    }


    /**
     * Calculate the sum of the node values at each level
     *
     * @param model 数据
     */
    private static void calculationLevelNodeValue(SanKeyModel model) {
        List<Level> levelList = model.getLevelList();
        float maxLevelValueSum = model.getMaxLevelValueSum();
        for (Level level : levelList) {
            float valueSum = 0;
            List<Node> nodes = level.getNodes();
            level.setNum(nodes.size());
            for (Node node : nodes) {
                valueSum += node.getValue();
            }
            level.setValueSum(valueSum);
            //计算层级之和的最大值
            if (maxLevelValueSum < valueSum) {
                maxLevelValueSum = valueSum;
            }
        }
        model.setMaxLevelValueSum(maxLevelValueSum);
        model.setNodePadding(maxLevelValueSum * 0.08f);
    }

    /**
     * Calculate the sum of the value of each level and the interval
     *
     * @param model 数据
     */
    private static void calculationLevelNodeAndPaddingSum(SanKeyModel model) {
        List<Level> levelList = model.getLevelList();
        float maxLevelValueAndPaddingSum = model.getMaxLevelValueAndPaddingSum();
        float nodePadding = model.getNodePadding();
        float maxLevelValueSum = model.getMaxLevelValueSum();
        for (Level level : levelList) {
            float paddingSum = level.getNum() * maxLevelValueSum * 2 + (level.getNum() - 1 * nodePadding);
            level.setValueAndPaddingSum(paddingSum);
            if (level.getValueAndPaddingSum() > maxLevelValueAndPaddingSum) {
                maxLevelValueAndPaddingSum = level.getValueAndPaddingSum();
            }
        }
        model.setMaxLevelValueAndPaddingSum(maxLevelValueAndPaddingSum);
    }

}
