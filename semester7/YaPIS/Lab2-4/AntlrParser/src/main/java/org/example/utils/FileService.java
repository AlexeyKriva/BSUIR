package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public static String readCodeFromKovaFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
