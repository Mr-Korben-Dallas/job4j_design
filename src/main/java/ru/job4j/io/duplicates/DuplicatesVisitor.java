package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<String, String> mapOfFileProperties = new LinkedHashMap<>();
    private Set<String> setOfFileDuplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String filePath = file.toAbsolutePath().toString();
        String fileName = file.getFileName().toString();
        if (mapOfFileProperties.containsKey(fileName)) {
            setOfFileDuplicates.add(filePath);
            setOfFileDuplicates.add(mapOfFileProperties.get(fileName));
        } else {
            mapOfFileProperties.put(fileName, filePath);
        }
        return super.visitFile(file, attrs);
    }

    public Set<String> getListOfFileDuplicates() {
        return setOfFileDuplicates;
    }
}