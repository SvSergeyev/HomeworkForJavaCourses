package Homework.exam2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 8080;
    private  Connection connection;
    private ArrayBlockingQueue<Message> messages;
    private CopyOnWriteArraySet<Connection> connections;

    public void start() {
        messages = new ArrayBlockingQueue<>(Runtime.getRuntime().availableProcessors());
        connections = new CopyOnWriteArraySet<>();
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started at port " + PORT);
            Thread writerThread = new Thread(new Writer());
            writerThread.start();
            while (true) {
                Socket socket = server.accept();
                connection = new Connection(socket);
                connections.add(connection);
                if (!connections.isEmpty()) {
                    Thread readerThread = new Thread(new Reader(connection));
                    readerThread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Writer implements Runnable {

        @Override
        public void run() {
            System.out.println("Writer thread started.");
            while (true) {
                try {
                    Message outgoing = messages.take();
                    connection.send(outgoing);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Reader implements Runnable {
        private Connection connection;

        public Reader(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            System.out.println("Reader thread started.");
            while (true) {
                Message incoming = null;
                try {
                    incoming = connection.readMessage();
                    if (incoming != null) {
                        System.out.println("log: " + incoming);
                        messages.put(incoming);
                    }
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
