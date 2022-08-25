package com.company.algorithm;

import java.util.HashMap;

public class HuffmanData {
    private HashMap<Byte, Integer> table;
    private byte[] data;

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
