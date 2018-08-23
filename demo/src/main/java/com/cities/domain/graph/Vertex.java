package com.cities.domain.graph;

public final class Vertex<Element> {
	
    private final Element element;
    private volatile Edge connectedList;
    
    public Vertex(Element element, Edge connectedList) {
            this.element = element;
            this.setConnectedList(connectedList);
    }

	public Element getElement() {
		return element;
	}

	public Edge getConnectedList() {
		return connectedList;
	}

	public void setConnectedList(Edge connectedList) {
		this.connectedList = connectedList;
	}
}
