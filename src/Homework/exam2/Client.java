package Homework.exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private String name;

    private void start() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name:");
        try {
            name = reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid input.");
        }
        System.out.println("Connection...");
        try (Connection connection = new Connection(new Socket(Server.IP, Server.PORT))){
            System.out.println("Connected.");
            Thread readerThread = new Thread(new Reader(connection));
            System.out.println("Reader thread started.");
            while (true) {
                System.out.println("Enter your message:");
                String text = reader.readLine();
                sendMessage(new Message(name, text), connection);
                readerThread.start();
            }
        } catch (Exception e) {
            System.out.println("Client error.");
            e.printStackTrace();
        }
    }

    private void sendMessage(Message message, Connection connection) {
        try {
            connection.send(message);
            System.out.println("Server answer: " + connection.readMessage().toString());
        } catch (Exception e) {
            System.out.println("Message sending error.");
        }
    }

    private class Reader implements Runnable {
        private Connection connection;

        public Reader(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Message incoming = connection.readMessage();
                    System.out.println(incoming.toString());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
