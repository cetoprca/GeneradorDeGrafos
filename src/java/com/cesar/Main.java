package com.cesar;

import com.cesar.search.Search;
import com.cesar.graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(20, 30, 0, 20, false, false);

        graph.genNodes();
        graph.genVertices();
        graph.purge();

        graph.exportRelations();

        Search.populateNIDtI(graph);
        Search.populateNTV(graph);

        Search.exploreBFS(graph);

        System.out.println(Search.nodesLeft.isEmpty() ? "All nodes reached" : "Not all nodes reached");
    }
}