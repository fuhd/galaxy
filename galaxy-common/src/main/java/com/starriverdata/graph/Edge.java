package com.starriverdata.graph;

import lombok.Data;

@Data
public class Edge {

    private GraphNode nodeA;
    private GraphNode nodeB;

    public Edge(GraphNode nodeA, GraphNode nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }
}
