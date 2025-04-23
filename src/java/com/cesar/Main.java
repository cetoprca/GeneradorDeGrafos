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

        System.out.println(Search.explore(graph, 0) ? "All nodes reached" : "Not all nodes reached");

        System.out.println(Search.explore(graph, 1) ? "All nodes reached" : "Not all nodes reached");
    }
}