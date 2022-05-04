package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(line -> !(line.isBlank() || line.startsWith("#")))
                    .forEach(line -> {
                        int index = line.indexOf("=");
                        checkKeyValuePatternMatching(line, index);
                        values.put(
                                line.substring(0, index),
                                line.substring(index + 1)
                        );
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int getSizeValues() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    private void checkKeyValuePatternMatching(String line, int index) {
        int valuesLength = line.split("=").length;
        if (valuesLength <= 2 && index <= 0 || valuesLength < 2) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
