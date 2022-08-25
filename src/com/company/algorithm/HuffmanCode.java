package com.company.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public final class HuffmanCode {
    private HuffmanNode root;

    public HuffmanCode(HuffmanNode root) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (HuffmanCode) obj;
        return Objects.equals(this.root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return "HuffmanCode[" +
                "root=" + root + ']';
    }

    public HuffmanNode root() {
        return root;
    }


}
