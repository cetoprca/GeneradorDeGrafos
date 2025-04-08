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

    /**
     * Generates a random ammount of nodes depending on nodeAmmount
     * The number of nodes depends on the nodeAmmount, if the ammount is set as -1
     * it will generate a random ammount of nodes between 1 and 100
     */
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

    /**
     * Generates random vertices between random nodes with random weight
     * The number of vertices depends on the verticesAmmount, if the ammount is set as -1
     * it will generate a random ammount of vertices between 1 and 100
     */
    public void genVertices(){
        //If verticesAmmount is set as -1 the ammount of vertices generated will be random from 1 to 100
        if (verticesAmmount == -1){
            Random r = new Random();
            verticesAmmount = r.nextInt(1,100);
        }

        //If only 1 node is generated, reflexiveness is disabled and vertices must be generated return since the parameters are contradictory
        if (nodes.size() == 1 & verticesAmmount > 0 &!reflexive){
            System.out.println("[ ERROR ] The given parameters are contradictory. No vertices can generate");
            return;
        }

        //Warn the user min and max weights have to be min < max or else the weight wont work
        if (minWeight >= maxWeight & !weightWarning){
            weightWarning = true;
            System.out.println("[ ERROR ] Minimum weight must be smaller than maximum weight. Weights set to 0");
        }

        //Generate two random nodes, if reflexiveness is disabled the second node will re-generate if it ends up being the first one
        //If the weight warning has been activated, the weight will be 0, otherwise it will be a random value between minWeight and maxWeight
        //The first node will connect to the second node
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

    /**
     * Removes all nodes without outwards relations (Aka dead ends)
     */
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

    /**
     * Scans the nodes in the graph and generates a string containing their IDs and the IDs of the nodes they connect to.
     * [Origin Node] [Target Node] [Weight]
     * @return String containing all relations between nodes in different lines. Ex: A B 3
     */
    public String getRelations(){
        StringBuilder relations = new StringBuilder();

        for (Node node : nodes){
            String nodeID = getID(node.nodeid);

            for (int vertice : node.vertices.keySet()) {
                String verticeID = getID(vertice);

                relations.append(nodeID).append(" ").append(verticeID).append(" ").append(node.vertices.get(vertice)).append("\n");
            }
        }

        relations.deleteCharAt(relations.length()-1);
        return relations.toString();
    }

    /**
     * Obtain an alphanumerical ID based in the number provided.
     * @param id Number to be turned into alphanumerical ID
     * @return Alphanurical ID. Ex: A, B3, X48
     */
    public static String getID(int id) {
        StringBuilder ID = new StringBuilder();

        ID.append((char) ('A' + id % 26));

        if (id/26 != 0){
            ID.append(id/26);
        }
        return ID.toString();
    }
}
