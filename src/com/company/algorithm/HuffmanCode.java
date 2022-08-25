package com.company.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class HuffmanCode {
    HuffmanNode root;
    public HuffmanCode(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    private void getCode(HashMap<Byte, String> result, String code, HuffmanNode node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            result.put(node.getData(), code.isEmpty() ? "1" : code);
        }
        getCode(result, code + '0', node.getLeft());
        getCode(result, code + '1', node.getRight());
    }

    public String encode(byte[] bytes) {
        HashMap<Byte, String> codes = new HashMap<>();
        getCode(codes, "", root);
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(codes.get(b));
        }
        return builder.toString();
    }

    public byte[] decode(String code) {
        HuffmanNode temp = root;
        ArrayList<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < code.length(); ++i) {
            temp = (code.charAt(i) == '1') ? temp.getRight() : temp.getLeft();
            if (temp.isLeaf()) {
                bytes.add(temp.getData());
                temp = root;
            }
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }
}
