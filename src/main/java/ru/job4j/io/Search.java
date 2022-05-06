package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static Path filePath;
    private static String fileExtension;

    public static void main(String[] args) throws IOException {
        Search.validateInput(args);
        search(filePath, p -> p.toFile().getName().endsWith(fileExtension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateInput(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("You need enter two arguments, first is path, second is file extension");

        }
        filePath = Paths.get(args[0]);
        if (!Files.exists(filePath)) {
            throw new IOException("Entered path does not exists.");
        }
        fileExtension = args[1];
        if (!fileExtension.startsWith(".")) {
            throw new IOException("File extension must start with a dot.");
        }
    }
}