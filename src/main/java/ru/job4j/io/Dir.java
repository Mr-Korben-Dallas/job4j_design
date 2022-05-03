package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\dev\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist file %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not exist directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("total size is: %s Mb%n", file.getTotalSpace());
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isDirectory()) {
                System.out.printf("%s%n", subfile.getName());
            } else {
                System.out.printf("%s size is: %d Kb%n", subfile.getName(), subfile.length());
            }
        }
    }
}