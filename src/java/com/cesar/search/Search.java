package com.cesar.search;

import com.cesar.graph.Graph;
import com.cesar.graph.Node;

import java.util.*;

public class Search {
    public static List<Integer> nodesToVisit = new ArrayList<>();
    public static Set<Integer> nodesLeft = new HashSet<>();
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
            nodesLeft.add(node.nodeid);
        }
    }

    /**
     * Depth First Search algorithm for the graphs generated in this same program.
     * @param graph Graph to be searched.
     * @param node Node to be visited.
     * @param origin Wether this is the node of origin for the current search.
     */
    public static void exploreDFS(Graph graph, Node node, boolean origin){
        //If the node to be searched has NOT been visited, remove it from the list
        if (nodesLeft.contains(node.nodeid)){
            nodesLeft.remove(node.nodeid);
            //search every node the current node points to
            for (int id : node.vertices.keySet()){
                if (nodesLeft.contains(id)){
                    System.out.println(Graph.getID(node.nodeid) + " -> " + Graph.getID(id));
                }
                try {
                    exploreDFS(graph, graph.nodes.get(nodeIDToIndex.get(id)), false);
                }catch (NullPointerException e){
                    System.out.println("[ WARNING ] DFS reached a dead end corresponding to node " + Graph.getID(id));
                }
            }
        }
        //If the node is an origin node, but the graph has not been completly visited, visit the first node in nodesToVisit as an origin node
        if (origin & !nodesLeft.isEmpty()) {
            exploreDFS(graph, graph.nodes.get(nodeIDToIndex.get(nodesLeft.iterator().next())), true);
        }
    }

    /**
     * Breath First Search algorithm for the graphs generated in this same program.
     * @param graph Graph to be searched.
     */
    public static void exploreBFS(Graph graph){
        nodesToVisit.add(nodesLeft.iterator().next());
        while (!nodesLeft.isEmpty()){
            while (!nodesToVisit.isEmpty()){
                if (nodesLeft.contains(nodesToVisit.getFirst())){
                    System.out.println(Graph.getID(nodesToVisit.getFirst()));

                    nodesLeft.remove(nodesToVisit.getFirst());
                    nodesToVisit.addAll(graph.nodes.get(nodeIDToIndex.get(nodesToVisit.getFirst())).vertices.keySet());

                    nodesToVisit.removeFirst();

                    if (nodesToVisit.isEmpty() && !nodesLeft.isEmpty()){
                        nodesToVisit.add(nodesLeft.iterator().next());
                    }
                }else{
                    nodesToVisit.removeFirst();

                    if (nodesToVisit.isEmpty() && !nodesLeft.isEmpty()){
                        nodesToVisit.add(nodesLeft.iterator().next());
                    }
                }
            }
        }
    }
}
