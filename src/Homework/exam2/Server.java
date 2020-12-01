package Homework.exam2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
//    private Connection connection;
    private static Socket client;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    private CopyOnWriteArraySet clients;

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(8090)) {
            System.out.println("Server started...");
            while (true) {

            }
        }
    }

}
