package com.company.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

final class HuffmanBuilder {
    private static Map<Character, Integer> countFrequencies(String text) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < text.length(); ++i) {
            result.put(text.charAt(i), result.getOrDefault(text.charAt(i), 0) + 1);
        }
        return result;
    }

    private static PriorityQueue<HuffmanNode> createNodesQueue(String text) {
        Map<Character, Integer> frequencies = countFrequencies(text);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(Comparator.comparingInt(HuffmanNode::getFrequency));
        frequencies.forEach((key, value) -> queue.add(new HuffmanNode(key, value)));
        return queue;
    }

    public static HuffmanNode createTree(String text) {
        PriorityQueue<HuffmanNode> nodes = createNodesQueue(text);
        while (nodes.size() > 1) {
            HuffmanNode left = nodes.poll(), right = nodes.poll();
            int sum = left.getFrequency() + right.getFrequency();
            HuffmanNode totalNode = new HuffmanNode(null, sum, left, right);
            nodes.add(totalNode);
        }
        return nodes.poll();
    }
}
