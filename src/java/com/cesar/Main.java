package com.cesar;

import com.cesar.search.Search;
import com.cesar.graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(20, 50, 0, 20, true, false);

        graph.genNodes();
        graph.genVertices();
        graph.purge();

        graph.exportRelations();

        Search.populateNIDtI(graph);
        Search.populateNTV(graph);

        Search.exploreDFS(graph, graph.nodes.getFirst(), true);

        System.out.println(Search.nodesToVisit.isEmpty() ? "All nodes reached" : "Not all nodes reached");
    }
}