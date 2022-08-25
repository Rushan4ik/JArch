package com.company.algorithm;


import java.util.HashMap;

public class ArchiverFacade {
    public static HuffmanData pack(byte[] data) {
        HashMap<Byte, Integer> table = HuffmanBuilder.countFrequencies(data);
        HuffmanNode node = HuffmanBuilder.build(table);
        HuffmanCode code = new HuffmanCode(node);
        String binaryString = code.encode(data);
        return new HuffmanData(table, ByteHelper.convert(binaryString));
    }

    public static byte[] unpack(HuffmanData data) {
        HuffmanCode code = new HuffmanCode(data.getRoot());
        byte[] encode = data.getData();
        String binaryString = ByteHelper.convert(encode);
        return code.decode(binaryString);
    }
}
