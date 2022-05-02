package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        String matcher = "^[^\"]*\"[^\"]*\\sHTTP\\/[\\d.]+\"\\s+(404)(.*)+";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .filter(line -> line.matches(matcher))
                    .forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            for (String line : log) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }
}
