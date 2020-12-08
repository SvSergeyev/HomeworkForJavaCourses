package Homework.exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    public static final int PORT = 8090;
    public static final String IP = "127.0.0.1";
    public static CopyOnWriteArraySet<Connection> connections;
    public static ArrayBlockingQueue<Message> messages;

    public void start() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Thread writerThread = new Thread(new Writer());
            writerThread.start();
            while (!server.isClosed()) {
                System.out.println("/exit to close all connections and shutdown the server");
                String serverCommand= reader.readLine();
                if (serverCommand.equals("/exit")) {
                    System.out.println("Shutting down the server...");
                    server.close();
                    break;
                }
                Socket client = server.accept();
                Connection newConnection = new Connection(client);
                connections = new CopyOnWriteArraySet<>();
                messages = new ArrayBlockingQueue<>(Runtime.getRuntime().availableProcessors());
                connections.add(newConnection);
                System.out.println("New connection accepted!");
                Thread readerThread = new Thread(new Reader(newConnection));
                readerThread.start();
            }
            if (!connections.isEmpty()) {
                for (Connection connection : connections) {
                    connection.close();
                }
            }
        } catch (IOException e) {
            System.out.println("The server was interrupted.");
        } catch (NullPointerException e) {
            System.out.println("No active connections.");
        } catch (Exception e) {
            System.out.println("Connection closing error.");
        }
    }

    private class Reader implements Runnable {
        private final Connection connection;

        public Reader(Connection connection) {
            this.connection= connection;
        }

        @Override
        public void run() {
            try {
                while (!connection.getSocket().isClosed()) {
                    Message incoming = (Message) connection.getIn().readObject();
                    connection.setSender(incoming.getSender());
                    System.out.println("log: " + incoming);
                    if (incoming.getText().equals("/exit")) {
                        System.out.println("Client initiated shutdown.");
                        connection.getOut().writeObject(new Message("Server", "Your connection will be closed."));
                        break;
                    }
                    Server.messages.put(incoming);
                }
                System.out.println("Closing connection...");
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class Writer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Message outgoing = Server.messages.take();
                    System.out.println("log: " + outgoing);
                    for (Connection connection : Server.connections) {
                        if (connection.getSender().equals(outgoing.getSender())) {
                            connection.getOut().writeObject(outgoing);
                            connection.getOut().flush();
                        }
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
