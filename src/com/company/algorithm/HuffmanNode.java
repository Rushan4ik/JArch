package com.company.algorithm;

public final class HuffmanNode {
    private final Character symbol;
    private final int frequency;
    private HuffmanNode left = null, right = null;

    public HuffmanNode(Character symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    public HuffmanNode(Character symbol, int frequency, HuffmanNode left, HuffmanNode right) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public Character getSymbol() {
        return symbol;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }
}
