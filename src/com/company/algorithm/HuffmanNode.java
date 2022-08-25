package com.company.algorithm;

public class HuffmanNode {
    private final Byte data;
    private HuffmanNode left, right;

    public HuffmanNode(Byte data) {
        this.data = data;
    }

    public HuffmanNode(Byte data, HuffmanNode left, HuffmanNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Byte getData() {
        return data;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("HuffmanNode\n{");
        if (isLeaf()) {
            return builder.append("data=").append(data).append("}\n").toString();
        }
        return builder.append("left=\n").append(left).append("right=").append(right).toString();
    }
}
