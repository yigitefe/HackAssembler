package com.hack;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    public static String get15BitBinaryRepresenation(int value) {
        String asBinary = Integer.toBinaryString(value);
        return ("000000000000000" + asBinary).substring(asBinary.length());
    }

    public static boolean isNumeric(String value) {
        if (value == null) return false;
        try {
            int i = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getFileNameWithoutExtension(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        return fileName.substring(0, fileName.indexOf("."));
    }
}
