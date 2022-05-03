package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {
    private String source = "";
    private String target = "";

    public Analysis(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public void unavailable() {
        boolean isServerDown = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(this.target))) {
            List<String> lineLists = reader.lines().toList();
            for (String line : lineLists) {
                String[] logText = line.split(" ");
                int serverCode = Integer.parseInt(logText[0]);
                if (((serverCode == 400 || serverCode == 500) && !isServerDown)) {
                    writer.print(logText[1] + ";");
                    isServerDown = true;
                }
                if (serverCode == 300 || serverCode == 200 && isServerDown) {
                    writer.println(logText[1]);
                    isServerDown = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis("server.log", "unavailable.csv");
        analysis.unavailable();
    }
}
