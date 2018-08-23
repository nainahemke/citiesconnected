package com.cities.domain.graph;

public final class Edge {
	
    private final int vertexIndex;
    private final Edge next;
    
    public Edge(int vertexIndex, Edge next) {
            this.vertexIndex = vertexIndex;
            this.next = next;
    }

	public int getVertexIndex() {
		return vertexIndex;
	}

	public Edge getNext() {
		return next;
	}
}
