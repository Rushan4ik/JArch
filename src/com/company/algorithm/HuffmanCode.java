package com.company.algorithm;

import java.util.HashMap;
import java.util.Map;

public final class HuffmanCode {
    private static Map<Character, String> getCodes(HuffmanNode root) {
        if (root == null) {
            return null;
        }

        Map<Character, String> result = new HashMap<>();
        if (root.isLeaf()) {
            result.put(root.getSymbol(), "1");
            return result;
        }

        getCodes(root, "", result);
        return result;
    }

    private static void getCodes(HuffmanNode root, String code, Map<Character, String> result) {
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            result.put(root.getSymbol(), code);
            return;
        }

        getCodes(root.getLeft(), code + '1', result);
        getCodes(root.getRight(), code + '0', result);
    }

    public static String encode(String text) {
        HuffmanNode root = HuffmanBuilder.createTree(text);
        Map<Character, String> codes = getCodes(root);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            result.append(codes.get(text.charAt(i)));
        }
        return result.toString();
    }
}
