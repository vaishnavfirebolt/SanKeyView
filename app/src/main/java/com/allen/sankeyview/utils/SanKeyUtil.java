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
 * 桑基图的工具类
 *
 * @author Renjy
 */
public class SanKeyUtil {
    /**
     * 计算桑基图的数据
     *
     * @param model 数据
     */
    public static void calculationSanKeyModel(SanKeyModel model) {
        //获取所有根节点
        getRootNodes(model);
        //计算所有的线和每个节点的级别（从根节点到尾节点）
        calculationAllLinks(model);
        //计算所有节点的数值(比重值)
        calculationNodesValue(model);
        //计算层级相关数据
        calculationLevel(model);
        //计算每一层级数值总和（比重总和）
        calculationLevelNodeValue(model);
        //计算每一层级数值和间隔之和（间隔为层级最大值的百分之）
        calculationLevelNodeAndPaddingSum(model);
    }


    /**
     * 获取根节点（顶级节点）
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
     * 计算所有的连接线，从根节点到尾节点
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
     * 获取下一个节点
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
     * 通过节点的名称获取对应的节点
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
     * 计算节点的数值
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
     * 计算层级相关数据
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
     * 添加节点到层级中
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
     * 计算每一层级节点数值的总和
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
        model.setNodePadding(maxLevelValueSum * 0.02f);
    }

    /**
     * 计算每一层级数值和间隔之和（间隔为最大值的百分之二）
     *
     * @param model 数据
     */
    private static void calculationLevelNodeAndPaddingSum(SanKeyModel model) {
        List<Level> levelList = model.getLevelList();
        float maxLevelValueAndPaddingSum = model.getMaxLevelValueAndPaddingSum();
        float nodePadding = model.getNodePadding();
        for (Level level : levelList) {
            float paddingSum = (level.getNum() - 1) * nodePadding;
            level.setValueAndPaddingSum(level.getValueSum() + paddingSum);
            if (level.getValueAndPaddingSum() > maxLevelValueAndPaddingSum) {
                maxLevelValueAndPaddingSum = level.getValueAndPaddingSum();
            }
        }
        model.setMaxLevelValueAndPaddingSum(maxLevelValueAndPaddingSum);
    }

}
