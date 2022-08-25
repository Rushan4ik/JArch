package com.company.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ArchiverCommand {
    File source;
    boolean archive;

    public ArchiverCommand(File source) {
        this.source = source;
        archive = !isArchive();
    }

    private boolean isArchive() {
        return source.getName().endsWith(".jarch");
    }

    public void execute() throws IOException {
        if (archive) {
            writeToArchive();
        } else {
            writeToFile();
        }
    }

    private void writeToFile() throws IOException {
        String fileName = source.getName().substring(0, source.getName().length() - ".jarch".length());
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            HuffmanData data = readArchive();
            outputStream.write(ArchiverFacade.unpack(data));
        }
    }

    private HuffmanData readArchive() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(source)) {
            HashMap<Byte, Integer> table = readTable(inputStream);
            return new HuffmanData(table, inputStream.readAllBytes());
        }
    }

    private HashMap<Byte, Integer> readTable(FileInputStream inputStream) throws IOException {
        HashMap<Byte, Integer> result = new HashMap<>();
        int tableSize = new BigInteger(inputStream.readNBytes(4)).intValue();
        for (int i = 0; i < tableSize; ++i) {
            byte key = inputStream.readNBytes(1)[0];
            int value = new BigInteger(inputStream.readNBytes(4)).intValue();
            result.put(key, value);
        }
        return result;
    }

    private void writeToArchive() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(source)) {
            HuffmanData encodingData = ArchiverFacade.pack(inputStream.readAllBytes());
            write(encodingData);
        }
    }

    private void write(HuffmanData encodingData) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(source.getName() + ".jarch")) {
            writeTable(outputStream, encodingData.getTable());
            outputStream.write(encodingData.getData());
        }
    }

    private void writeTable(FileOutputStream outputStream, HashMap<Byte, Integer> table) throws IOException {
        int tableSize = table.size();
        outputStream.write(ByteBuffer.allocate(4).putInt(tableSize).array());
        for (Map.Entry<Byte, Integer> entry : table.entrySet()) {
            outputStream.write(new byte[]{entry.getKey()});
            outputStream.write(ByteBuffer.allocate(4).putInt(entry.getValue()).array());
        }
    }
}
