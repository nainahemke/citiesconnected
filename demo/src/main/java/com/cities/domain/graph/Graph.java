package com.cities.domain.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class Graph<Element> {

	private final Vertex<Element>[] connectedList;
	private final Set<Set<Integer>> connectedNodesGroup;

	public Graph(List<Element> uniqueCities, Set<DirectPair<Element>> pairs) {
		
		int countOfUniqueCities = uniqueCities.size();
		connectedList = new Vertex[countOfUniqueCities];

		for (int idx = 0; idx < connectedList.length; idx++) {
			connectedList[idx] = new Vertex<Element>(uniqueCities.get(idx), null);
		}

		Iterator<DirectPair<Element>> iterator = pairs.iterator();
		while (iterator.hasNext()) {
			DirectPair<Element> pair = iterator.next();
			int idx1 = indexForElement(pair.getLeft());
			int idx2 = indexForElement(pair.getRight());

			connectedList[idx1]
					.setConnectedList(new Edge(idx2, connectedList[idx1].getConnectedList()));
			connectedList[idx2]
					.setConnectedList(new Edge(idx1, connectedList[idx2].getConnectedList()));
		}
		this.connectedNodesGroup = getConnectedNodes();
	}

	private Set<Set<Integer>> getConnectedNodesGroup() {
		return connectedNodesGroup;
	}

	private int indexForElement(Element element) {
		for (int idx = 0; idx < connectedList.length; idx++) {
			if (connectedList[idx].getElement().equals(element)) {
				return idx;
			}
		}
		return -1;
	}

	public boolean isPathPresent(Element element1, Element element2) {
		int indexVertex1 = indexForElement(element1);
		int indexVertex2 = indexForElement(element2);
		if (indexVertex1 == -1 || indexVertex2 == -1)
			return false;


		Optional<Set<Integer>> findFirst = getConnectedNodesGroup().stream()
				.filter(set -> set.contains(indexVertex1) && set.contains(indexVertex2)).findFirst();
		return findFirst.isPresent();
	}

	private void collect(int indexStart, Set<Integer> vistedNodes, Set<Integer> connectedNodes) {
		vistedNodes.add(indexStart);
		connectedNodes.add(indexStart);
		for (Edge edge = connectedList[indexStart].getConnectedList(); edge != null; edge = edge.getNext()) {
			if (!vistedNodes.contains(edge.getVertexIndex())) {
				collect(edge.getVertexIndex(), vistedNodes, connectedNodes);
			}
		}
	}

	private Set<Set<Integer>> getConnectedNodes() {
		Set<Integer> visitedNodes = new HashSet<>();
		Set<Set<Integer>> connectedNodesGroup = new HashSet<>();
		for (int idx = 0; idx < connectedList.length; idx++) {
			if (!visitedNodes.contains(idx)) {
				Set<Integer> connectedNodes = new HashSet<>();
				connectedNodesGroup.add(connectedNodes);
				collect(idx, visitedNodes, connectedNodes);
			}
		}
		return connectedNodesGroup;
	}

}
