package com.cities.domain.graph;

@SuppressWarnings("hiding")
public final class DirectPair<String> {
	
	private final String left;
	private final String right;
	
	public DirectPair(String left, String right) {
		this.left = left;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}
}
