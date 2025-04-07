package com.cesar.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public int nodeid;
    public Map<Integer, Integer> vertices = new HashMap<>();

    public Node(int nodeid){
        this.nodeid = nodeid;
    }

    public void connectNode(Node node, int weight, boolean directional){
        vertices.put(node.nodeid, weight);
        if (!directional){
            node.vertices.put(nodeid, weight);
        }
    }
}
