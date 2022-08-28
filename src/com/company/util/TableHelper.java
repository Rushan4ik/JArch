package com.company.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Class write/read hash-map of frequencies
 * format
 * 4 byte - size of map
 * next 5 * size bytes:
 * [0] - value,
 * [1..4] - frequency
 */

public final class TableHelper {
    public static void writeTable(HashMap<Byte, Integer> frequency, FileOutputStream outputStream) throws IOException {
        outputStream.write(ByteBuffer.allocate(4).putInt(frequency.size()).array());
        for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {
            outputStream.write(new byte[]{entry.getKey()});
            outputStream.write(ByteBuffer.allocate(4).putInt(entry.getValue()).array());
        }
    }

    public static HashMap<Byte, Integer> readTable(FileInputStream inputStream) throws IOException {
        HashMap<Byte, Integer> result = new HashMap<>();
        int tableSize = new BigInteger(inputStream.readNBytes(4)).intValue();
        for (int i = 0; i < tableSize; ++i) {
            byte key = inputStream.readNBytes(1)[0];
            int value = new BigInteger(inputStream.readNBytes(4)).intValue();
            result.put(key, value);
        }
        return result;
    }
}
