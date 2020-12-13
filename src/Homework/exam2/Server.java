package Homework.exam2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 8080;
    private static ArrayBlockingQueue<Message> messages;
    private static CopyOnWriteArraySet<Connection> connections;

    public void start() {
        messages = new ArrayBlockingQueue<>(Runtime.getRuntime().availableProcessors());
        connections = new CopyOnWriteArraySet<>();
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started at port " + PORT);
            Thread writerThread = new Thread(new Writer());
            writerThread.start();
            while (!server.isClosed()) {
                Socket socket = server.accept();
                Connection connection = new Connection(socket);
                connections.add(connection);
                Thread reader = new Thread(new Reader(connection));
                reader.start();
            }
        } catch (IOException e) {
            System.out.println("Server startup error.");
            e.printStackTrace();
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Message outgoing = messages.take();
                    for (Connection connection : connections) {
                        if (!outgoing.getSender().equals(connection.getSender())) {
                            connection.getOutput().writeObject(outgoing);
                            connection.getOutput().flush();
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    System.out.println("Server writer error.");
                    e.printStackTrace();
                }
            }
        }
    }


    private class Reader implements Runnable {
        private final Connection connection;

        public Reader(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Message incoming;
                    incoming = (Message) connection.getInput().readObject();
                    connection.setSender(incoming.getSender());
                    if (("/exit").equals(incoming.getTextOfMessage())) {
                        connections.remove(connection);
                        break;
                    }
                    System.out.println("log:" + incoming);
                    messages.put(incoming);
                }
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                System.out.println("Connection with " + connection.getSender() + " was closed.");
            } finally {
                connection.close();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
