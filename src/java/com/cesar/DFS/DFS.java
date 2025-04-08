package com.cesar.DFS;

import com.cesar.graph.Graph;
import com.cesar.graph.Node;

import java.util.HashSet;
import java.util.Set;

public class DFS {
    public static Set<Integer> nodesToVisit = new HashSet<>();
    public static int visitedNodeCount = 0;

    public static void populate(Graph graph){
        for (Node node : graph.nodes){
            nodesToVisit.add(node.nodeid);
        }
    }

    public static void explore(Graph graph, Node node){
        if (nodesToVisit.contains(node.nodeid)){
            nodesToVisit.remove(node.nodeid);
            visitedNodeCount++;
            System.out.println(Graph.getID(node.nodeid));
            for (int id : node.vertices.keySet()){
                explore(graph, graph.nodes.get(id));
            }
        }
    }
}
