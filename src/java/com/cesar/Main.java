package com.cesar;

import com.cesar.DFS.DFS;
import com.cesar.graph.Graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(20, 20, 0, 100, false, false);

        graph.genNodes();
        graph.genVertices();

        graph.exportRelations();

        DFS.populate(graph);

    }
}