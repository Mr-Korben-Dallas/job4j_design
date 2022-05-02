package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        String matcher = "(.*)+(404)(.*)+(\\d)+";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .filter(line -> line.matches(matcher))
                    .forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
