package com.cesar.search;

import com.cesar.graph.Graph;
import com.cesar.graph.Node;

import java.util.HashSet;
import java.util.Set;

public class Search {
    public static Set<Integer> nodesToVisit = new HashSet<>();

    //Adds to the nodesToVisit list all nodes
    public static void populate(Graph graph){
        for (Node node : graph.nodes){
            nodesToVisit.add(node.nodeid);
        }
    }

    /**
     * Depth First search algorithm for the graphs generated in this same program.
     * Not compatible with purged graphs.
     * @param graph Graph the search will take place in.
     * @param node Node to be visited.
     * @param origin Wether this is the node of origin for the current search.
     */
    public static void exploreDFS(Graph graph, Node node, boolean origin){
        //If the node to be searched has NOT been visited, remove it from the list and add 1 to nodesVisited
        if (nodesToVisit.contains(node.nodeid)){
            nodesToVisit.remove(node.nodeid);
            System.out.println(Graph.getID(node.nodeid));
            //search every node the current node points to
            for (int id : node.vertices.keySet()){
                exploreDFS(graph, graph.nodes.get(id), false);
            }
        }
        //If the node is an origin node, but the graph has not been completly visited, visit the first node in nodesToVisit as an origin node
        if (origin & !nodesToVisit.isEmpty()){
            exploreDFS(graph, graph.nodes.get((Integer) nodesToVisit.toArray()[0]), true);
        }
    }
}
