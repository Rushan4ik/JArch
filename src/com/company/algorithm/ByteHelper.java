package com.company.algorithm;

import static java.lang.Math.*;

public final class ByteHelper {
    private static byte convertString(String s) {
        StringBuilder builder = new StringBuilder(s);
        while (builder.length() < 8) {
            builder.append('0');
        }
        s = builder.toString();
        byte result = 0;
        for (int i = 0; i < s.length(); ++i) {
            result <<= 1;
            result = (byte) (result | (s.charAt(i) - '0'));
        }
        return result;
    }

    private static String convertByte(byte b) {
        char[] chars = new char[8];
        for (int i = 7; i >= 0; --i) {
            chars[i] = (char) ((b & 1) + '0');
            b >>= 1;
        }
        return new String(chars);
    }

    public static byte[] convert(String binaryString) {
        byte[] result = new byte[(binaryString.length() + 7) / 8 + 1];
        result[0] = (byte) (8 - (binaryString.length() % 8));
        for (int i = 0, j = 1; i < binaryString.length(); i += 8, ++j) {
            result[j] = convertString(binaryString.substring(i, min(i + 8, binaryString.length())));
        }

        return result;
    }

    public static String convert(byte[] array) {
        StringBuilder builder = new StringBuilder();
        byte garbage = array[0];
        for (int i = 1; i < array.length; ++i) {
            builder.append(convertByte(array[i]));
        }
        builder.setLength(builder.length() - garbage);
        return builder.toString();
    }
}
