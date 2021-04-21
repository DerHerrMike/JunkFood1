package com.mps.app.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class FileUtils {
    private static final String UTF8_BOM = "\uFEFF";

    public static String skipBOM(String firstLine) throws IOException
    {
        if (firstLine.startsWith(UTF8_BOM)) {
            firstLine = firstLine.substring(1);
        }
        return firstLine;
    }

    public static BufferedReader getReader(Path path) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(path.toFile());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        return bufferedReader;
    }

}
