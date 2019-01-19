package com.kevin.common;

/*
 * Created by renhongjiang on 2019/1/19.
 */

import com.kevin.entity.Function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/1/19 11:53
 */
public class Tree {

    private List<Node> nodes = new LinkedList<>();
    private Node root = null;

    public Tree(List<Function> functions) {
        for (Function function : functions) {
            Node node = new Node(function.getId(), function.getParentId(),
                    function.getName(), "open",
                    new NodeAttribute(function.getUrl() == null ? "" : function.getUrl(), function.getId()),
                    function.getSerialNum());
            nodes.add(node);
            if (node.getId() == 0) {
                root = node;
            }
        }
    }

    public List<Node> build() {
        buildTree(root);
        List<Node> result = new ArrayList<>();
        result.add(root);
        return result;
    }

    private void buildTree(Node parent) {
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getParentId().equals(parent.getId())) {
                parent.getChildren().add(next);
                buildTree(next);
            }
        }
        /*for (Node node : nodes) {
            if (node.getParentId().equals(parent.getId())) {
                parent.getChildren().add(node);
                buildTree(node);
            }
        }*/


    }


}