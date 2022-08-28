package com.company.util.algorithm;

import java.util.HashMap;

public final class HuffmanData {
    private final HashMap<Byte, Integer> table;
    private final byte[] data;

    public HuffmanData(HashMap<Byte, Integer> table, byte[] data) {
        this.table = table;
        this.data = data;
    }

    public HuffmanNode getRoot() {
        return HuffmanBuilder.build(table);
    }

    public byte[] getData() {
        return data;
    }

    public HashMap<Byte, Integer> getTable() {
        return table;
    }
}
