package Homework.exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    public static final int PORT = 8090;
    public static final String IP = "127.0.0.1";
    public static ConcurrentHashMap<String, Connection> connections;
    public static ArrayBlockingQueue<Message> messages;
    BufferedReader serverCommand;

    private Connection connection;

    public void start() throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(8090)){
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                connection = new Connection(socket);
                System.out.println(connection.readMessage());
                connection.sendMessage(Message.getMessage("server", "получено"));
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
