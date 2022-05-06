package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> mapOfFileProperties = new LinkedHashMap<>();
    private Map<Path, FileProperty> mapOfFileDuplicates = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (mapOfFileProperties.containsKey(fileProperty)) {
            mapOfFileDuplicates.put(file, fileProperty);
            mapOfFileDuplicates.put(mapOfFileProperties.get(fileProperty), fileProperty);
        } else {
            mapOfFileProperties.put(fileProperty, file);
        }
        return super.visitFile(file, attrs);
    }

    public Map<Path, FileProperty> getListOfFileDuplicates() {
        return mapOfFileDuplicates;
    }
}