package com.company.util.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public final class HuffmanBuilder {
    public static HashMap<Byte, Integer> countFrequencies(byte[] data) {
        HashMap<Byte, Integer> result = new HashMap<>();
        for (byte e : data) {
            result.put(e, result.getOrDefault(e, 0) + 1);
        }
        return result;
    }

    private static PriorityQueue<HuffmanNodePair> createQueue(HashMap<Byte, Integer> frequencies) {
        PriorityQueue<HuffmanNodePair> queue = new PriorityQueue<>(Comparator.comparingInt(HuffmanNodePair::getFrequency));
        frequencies.forEach((key, value) -> queue.add(new HuffmanNodePair(new HuffmanNode(key), value)));
        return queue;
    }

    public static HuffmanNode build(HashMap<Byte, Integer> frequencies) {
        PriorityQueue<HuffmanNodePair> queue = createQueue(frequencies);
        while (queue.size() > 1) {
            HuffmanNodePair left = queue.poll();
            HuffmanNodePair right = queue.poll();
            queue.add(new HuffmanNodePair(new HuffmanNode(left.node, right.node),
                    left.frequency + right.frequency));
        }
        return queue.poll().node;
    }

    private static class HuffmanNodePair {
        HuffmanNode node;
        int frequency;

        public HuffmanNodePair(HuffmanNode node, int frequency) {
            this.node = node;
            this.frequency = frequency;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}
