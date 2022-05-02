package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("calculations.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = (i + 1) * (j + 1);
                    out.write((array[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void main(String[] args) {
        Matrix.multiple(5);
    }
}
