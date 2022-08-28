package com.company.util;

import com.company.util.algorithm.HuffmanData;

import java.io.*;
import java.util.HashMap;

public class ArchiverFacade {
    public static void toArchive(File file) throws IOException {
        writeToArchive(file);
    }

    public static void fromArchive(File file) throws IOException {
        HuffmanData data = readArchive(file);
        writeToFile(file, data);
    }

    private static HuffmanData readArchive(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            HashMap<Byte, Integer> table = TableHelper.readTable(inputStream);
            return new HuffmanData(table, inputStream.readAllBytes());
        }
    }

    private static void writeToFile(File file, HuffmanData data) throws IOException {
        String fileName = file.getName().substring(0, file.getName().length() - ".jarch".length());
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.write(PackerFacade.unpack(data));
        }
    }
    private static void writeToArchive(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            HuffmanData encodingData = PackerFacade.pack(inputStream.readAllBytes());
            writeData(encodingData, file);
        }
    }

    private static void writeData(HuffmanData encodingData, File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file.getName() + ".jarch")) {
            TableHelper.writeTable(encodingData.getTable(), outputStream);
            outputStream.write(encodingData.getData());
        }
    }
}
