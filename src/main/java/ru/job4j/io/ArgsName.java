package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        checkIsKeyExists(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String argValue : args) {
            String[] content = argValue.split("=", 2);
            String key = content[0].substring(1);
            String value = content[1];
            validateKeyValueNotEmpty(key, value);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        validateInput(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void validateInput(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You need enter at least one argument.");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("You entered the argument name incorrectly.");
            }
        }
    }

    private void checkIsKeyExists(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("You entered the argument name incorrectly, or it does not exist.");
        }
    }

    private void validateKeyValueNotEmpty(String key, String value) {
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("You entered the argument name incorrectly.");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}