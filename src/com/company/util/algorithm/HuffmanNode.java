package com.company.util.algorithm;

public final class HuffmanNode {
    private final Byte data;
    private HuffmanNode left, right;

    public HuffmanNode(Byte data) {
        this.data = data;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        data = null;
        this.left = left;
        this.right = right;
    }

    public Byte getData() {
        return data;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
