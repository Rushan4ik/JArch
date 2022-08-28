package com.company.util;

import com.company.util.algorithm.HuffmanData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public final class ArchiverHelper {
    public static void writeToArchive(File source) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(source)) {
            HuffmanData encodingData = ArchiverFacade.pack(inputStream.readAllBytes());
            writeData(source, encodingData);
        }
    }

    private static void writeData(File source, HuffmanData encodingData) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(source.getName() + ".jarch")) {
            TableHelper.writeTable(encodingData.getTable(), outputStream);
            outputStream.write(encodingData.getData());
        }
    }

    public static void writeToFile(File source) throws IOException {
        String fileName = source.getName().substring(0, source.getName().length() - ".jarch".length());
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            HuffmanData data = readArchive(source);
            outputStream.write(ArchiverFacade.unpack(data));
        }
    }

    private static HuffmanData readArchive(File source) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(source)) {
            HashMap<Byte, Integer> table = TableHelper.readTable(inputStream);
            return new HuffmanData(table, inputStream.readAllBytes());
        }
    }
}
