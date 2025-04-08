package com.cesar;

import com.cesar.search.Search;
import com.cesar.graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(1, 4, 0, 20, true, false);

        graph.genNodes();
        graph.genVertices();

        graph.exportRelations();

        Search.populate(graph);

        Search.exploreDFS(graph, graph.nodes.getFirst(), true);

        System.out.println(Search.nodesToVisit.isEmpty() ? "All nodes reached" : "Not all nodes reached");
    }
}