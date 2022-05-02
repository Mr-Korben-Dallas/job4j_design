package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(line);
                if (Integer.parseInt(line) % 2 == 0) {
                    lineBuilder.append(" - even number");
                } else {
                    lineBuilder.append(" - odd number");
                }
                System.out.println(lineBuilder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}