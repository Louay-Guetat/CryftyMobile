package com.codename1.entities;

public class Node {
    int id;
    String nodeLabel;
    String coidCode;
    float nodeReward;

    public Node(){

    }

    public Node(int id, String nodeLabel, String coidCode, float nodeReward) {
        this.id = id;
        this.nodeLabel = nodeLabel;
        this.coidCode = coidCode;
        this.nodeReward = nodeReward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public String getCoidCode() {
        return coidCode;
    }

    public void setCoidCode(String coidCode) {
        this.coidCode = coidCode;
    }

    public float getNodeReward() {
        return nodeReward;
    }

    public void setNodeReward(float nodeReward) {
        this.nodeReward = nodeReward;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", nodeLabel='" + nodeLabel + '\'' +
                ", coidCode='" + coidCode + '\'' +
                ", nodeReward=" + nodeReward +
                '}';
    }

}
