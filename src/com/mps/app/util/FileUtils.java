package com.mps.app.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/** class to remove a problem with invisible space before first character in UTF8 csv formats
 * @author MikeSchwingenschlögl
 */
public class FileUtils {
    private static final String UTF8_BOM = "\uFEFF";

    public static String skipBOM(String firstLine) {
        if (firstLine.startsWith(UTF8_BOM)) {
            firstLine = firstLine.substring(1);
        }
        return firstLine;
    }

    public static BufferedReader getReader(Path path) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(path.toFile());
        return new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
    }

}
