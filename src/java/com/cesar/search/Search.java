package com.cesar.search;

import com.cesar.graph.Graph;
import com.cesar.graph.Node;

import java.util.*;

public class Search {
    public static Set<Integer> nodesToVisit = new HashSet<>();
    private static final Map<Integer, Integer> nodeIDToIndex = new HashMap<>();

    //Maps every nodeID left in the graph to its node list index
    public static void populateNIDtI(Graph graph){
        List<Node> nodes = graph.nodes;
        for (int i = 0; i < nodes.size(); i++) {
            nodeIDToIndex.put(nodes.get(i).nodeid, i);
        }
    }

    //Adds to the nodesToVisit list all nodes
    public static void populateNTV(Graph graph){
        for (Node node : graph.nodes){
            nodesToVisit.add(node.nodeid);
        }
    }

    /**
     * Depth First search algorithm for the graphs generated in this same program.
     * @param graph Graph the search will take place in.
     * @param node Node to be visited.
     * @param origin Wether this is the node of origin for the current search.
     */
    public static void exploreDFS(Graph graph, Node node, boolean origin){
        //If the node to be searched has NOT been visited, remove it from the list
        if (nodesToVisit.contains(node.nodeid)){
            nodesToVisit.remove(node.nodeid);
            //search every node the current node points to
            for (int id : node.vertices.keySet()){
                System.out.println(Graph.getID(node.nodeid) + " -> " + Graph.getID(id));
                try {
                    exploreDFS(graph, graph.nodes.get(nodeIDToIndex.get(id)), false);
                }catch (NullPointerException e){
                    System.out.println("[ WARNING ] DFS reached a dead end corresponding to node " + Graph.getID(id));
                }
            }
        }
        //If the node is an origin node, but the graph has not been completly visited, visit the first node in nodesToVisit as an origin node
        if (origin & !nodesToVisit.isEmpty()) {
            exploreDFS(graph, graph.nodes.get(nodeIDToIndex.get(nodesToVisit.iterator().next())), true);
        }
    }
}
