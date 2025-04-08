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
            String nodeID = getID(node.nodeid);

            for (int verticeId : node.vertices.keySet()) {
                String verticeChar = getID(verticeId);

                relations.append(nodeID).append(" ").append(verticeChar).append(" ").append(node.vertices.get(verticeId)).append("\n");
            }
        }

        return relations.toString();
    }

    public static String getID(int id) {
        String ID = "";

        switch (id %26){
            case 0 -> ID = "A";
            case 1 -> ID = "B";
            case 2 -> ID = "C";
            case 3 -> ID = "D";
            case 4 -> ID = "E";
            case 5 -> ID = "F";
            case 6 -> ID = "G";
            case 7 -> ID = "H";
            case 8 -> ID = "I";
            case 9 -> ID = "J";
            case 10 -> ID = "K";
            case 11 -> ID = "L";
            case 12 -> ID = "M";
            case 13 -> ID = "N";
            case 14 -> ID = "O";
            case 15 -> ID = "P";
            case 16 -> ID = "Q";
            case 17 -> ID = "R";
            case 18 -> ID = "S";
            case 19 -> ID = "T";
            case 20 -> ID = "U";
            case 21 -> ID = "V";
            case 22 -> ID = "W";
            case 23 -> ID = "X";
            case 24 -> ID = "Y";
            case 25 -> ID = "Z";
        }

        if (id /26 != 0){
            ID = ID + id /26;
        }
        return ID;
    }
}
