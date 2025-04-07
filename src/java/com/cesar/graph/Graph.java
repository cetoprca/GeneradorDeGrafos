package com.cesar.graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private boolean weightWarning;
    private boolean reflexive;
    private boolean directional;
    private int minWeight = 0;
    private int maxWeight = 0;
    private int verticesAmmount = -1;
    private int nodeAmmount = -1;
    public List<Node> nodes = new ArrayList<>();

    public Graph(){}

    public Graph(int nodeAmmount, int verticesAmmount){
        this.nodeAmmount = nodeAmmount;
        this.verticesAmmount = verticesAmmount;
    }

    public Graph(int nodeAmmount, int verticesAmmount, int minWeight, int maxWeight){
        this.nodeAmmount = nodeAmmount;
        this.verticesAmmount = verticesAmmount;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public Graph(int nodeAmmount, int verticesAmmount, int minWeight, int maxWeight, boolean directional){
        this.nodeAmmount = nodeAmmount;
        this.verticesAmmount = verticesAmmount;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.directional = directional;
    }

    public Graph(int nodeAmmount, int verticesAmmount, int minWeight, int maxWeight, boolean directional, boolean reflexive){
        this.nodeAmmount = nodeAmmount;
        this.verticesAmmount = verticesAmmount;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.directional = directional;
        this.reflexive = reflexive;
    }

    public boolean isReflexive() {
        return reflexive;
    }

    public void setReflexive(boolean reflexive) {
        this.reflexive = reflexive;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getVerticesAmmount() {
        return verticesAmmount;
    }

    public void setVerticesAmmount(int verticesAmmount) {
        this.verticesAmmount = verticesAmmount;
    }

    public int getNodeAmmount() {
        return nodeAmmount;
    }

    public void setNodeAmmount(int nodeAmmount) {
        this.nodeAmmount = nodeAmmount;
    }

    public void genNodes(){
        if (nodeAmmount == -1){
            Random r = new Random();
            nodeAmmount = r.nextInt(1,100);
            genNodes();
        }else {
            for (int i = 0; i < nodeAmmount; i++) {
                nodes.add(new Node(i));
            }
        }
    }

    public void genVertices(){
        if (minWeight >= maxWeight & !weightWarning){
            weightWarning = true;
            System.out.println("[ ERROR ] Minimum weight must be smaller than maximum weight. Weights set to 0");
        }

        if (verticesAmmount == -1){
            Random r = new Random();
            verticesAmmount = r.nextInt(1,100);
            genVertices();
        }else {
            for (int i = 0; i < verticesAmmount; i++) {
                Random r = new Random();

                Node rNode1 = nodes.get(r.nextInt(0,nodes.size()));

                Node rNode2 = nodes.get(r.nextInt(0,nodes.size()));
                while (!reflexive & rNode2.nodeid == rNode1.nodeid){
                    rNode2 = nodes.get(r.nextInt(0,nodes.size()));
                }

                int rWeight;
                if (weightWarning){
                    rWeight = 0;
                }else rWeight = r.nextInt(minWeight,maxWeight);

                rNode1.connectNode(rNode2, rWeight, directional);
            }
        }
    }

    public void purge(){
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).vertices.isEmpty()){
                nodes.remove(i);
                i--;
            }
        }
    }

    public void exportRelations(){
        File relations = new File("relations.txt");
        try {
            FileWriter fileWriter = new FileWriter("relations.txt");

            fileWriter.write(getRelations());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("[ ERROR ] An error has occurred during the exporting");
            throw new RuntimeException(e);
        }
    }

    public void printRelations(){
        System.out.println(getRelations());
    }

    public String getRelations(){
        StringBuilder relations = new StringBuilder();

        for (Node node : nodes){
            String nodeID = getNodeID(node);

            for (int verticeId : node.vertices.keySet()) {
                String verticeChar = getVerticeID(verticeId);

                relations.append(nodeID).append(" ").append(verticeChar).append(" ").append(node.vertices.get(verticeId)).append("\n");
            }
        }

        return relations.toString();
    }

    private static String getNodeID(Node node) {
        String nodeChar = "";

        switch (node.nodeid%26){
            case 0 -> nodeChar = "A";
            case 1 -> nodeChar = "B";
            case 2 -> nodeChar = "C";
            case 3 -> nodeChar = "D";
            case 4 -> nodeChar = "E";
            case 5 -> nodeChar = "F";
            case 6 -> nodeChar = "G";
            case 7 -> nodeChar = "H";
            case 8 -> nodeChar = "I";
            case 9 -> nodeChar = "J";
            case 10 -> nodeChar = "K";
            case 11 -> nodeChar = "L";
            case 12 -> nodeChar = "M";
            case 13 -> nodeChar = "N";
            case 14 -> nodeChar = "O";
            case 15 -> nodeChar = "P";
            case 16 -> nodeChar = "Q";
            case 17 -> nodeChar = "R";
            case 18 -> nodeChar = "S";
            case 19 -> nodeChar = "T";
            case 20 -> nodeChar = "U";
            case 21 -> nodeChar = "V";
            case 22 -> nodeChar = "W";
            case 23 -> nodeChar = "X";
            case 24 -> nodeChar = "Y";
            case 25 -> nodeChar = "Z";
        }

        if (node.nodeid/26 != 0){
            nodeChar = nodeChar + node.nodeid/26;
        }
        return nodeChar;
    }
    private static String getVerticeID(int verticeId){
        String verticeChar = "";

        switch (verticeId%26){
            case 0 -> verticeChar = "A";
            case 1 -> verticeChar = "B";
            case 2 -> verticeChar = "C";
            case 3 -> verticeChar = "D";
            case 4 -> verticeChar = "E";
            case 5 -> verticeChar = "F";
            case 6 -> verticeChar = "G";
            case 7 -> verticeChar = "H";
            case 8 -> verticeChar = "I";
            case 9 -> verticeChar = "J";
            case 10 -> verticeChar = "K";
            case 11 -> verticeChar = "L";
            case 12 -> verticeChar = "M";
            case 13 -> verticeChar = "N";
            case 14 -> verticeChar = "O";
            case 15 -> verticeChar = "P";
            case 16 -> verticeChar = "Q";
            case 17 -> verticeChar = "R";
            case 18 -> verticeChar = "S";
            case 19 -> verticeChar = "T";
            case 20 -> verticeChar = "U";
            case 21 -> verticeChar = "V";
            case 22 -> verticeChar = "W";
            case 23 -> verticeChar = "X";
            case 24 -> verticeChar = "Y";
            case 25 -> verticeChar = "Z";
        }

        if (verticeId/26 != 0){
            verticeChar = verticeChar + verticeId/26;
        }

        return verticeChar;
    }
}
