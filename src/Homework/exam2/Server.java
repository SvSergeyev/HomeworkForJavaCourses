package Homework.exam2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    private Connection connection;
    private static final int PORT = 8090;

    public void start() throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                System.out.println(connection.readMessage());
                connection.sendMessage(Message.getMessage("SERVER", "RECEIVED"));
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
