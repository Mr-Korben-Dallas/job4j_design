package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> setOfFileProperties = new HashSet<>();
    private List<Path> listOfFileDuplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        boolean isAdded = setOfFileProperties.add(fileProperty);
        if (!isAdded) {
            listOfFileDuplicates.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getListOfFileDuplicates() {
        return this.listOfFileDuplicates;
    }
}