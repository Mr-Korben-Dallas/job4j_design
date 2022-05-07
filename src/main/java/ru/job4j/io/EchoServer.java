package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static String prepareAnswer(BufferedReader in, ServerSocket server) throws IOException {
        String answer = "";
        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
            if (str.contains("msg=")) {
                String param = str.split("msg=")[1].split(" ")[0];
                if (param.equals("Exit")) {
                    answer = "Bye";
                    server.close();
                }
                if (param.equals("Hello")) {
                    answer = "Hello";
                }
                if (param.equals("Any")) {
                    answer = "What?";
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String answer = prepareAnswer(in, server);
                    out.write(answer.getBytes());
                    out.flush();
                }
            }
        }
    }
}