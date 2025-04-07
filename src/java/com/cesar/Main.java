package com.cesar;

import com.cesar.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.setMinWeight(101);
        graph.setMaxWeight(100);

        graph.genNodes();
        graph.genVertices();
        graph.purge();

        graph.printRelations();
    }
}
