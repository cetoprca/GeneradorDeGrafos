package com.cesar.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public int nodeid;
    public Map<Integer, Integer> vertices = new HashMap<>();

    public Node(int nodeid){
        this.nodeid = nodeid;
    }

    /**
     * Connects the node called to the node provided.
     * @param node Node to connect to.
     * @param weight Weight of the relation.
     * @param directional Wether or not the target node should connect back.
     */
    public void connectNode(Node node, int weight, boolean directional){
        vertices.put(node.nodeid, weight);
        if (!directional){
            node.vertices.put(nodeid, weight);
        }
    }
}
