package Homework.exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private String name;
    private Connection connection;


    public void setClientName() {
        try {
            System.out.println("Enter your name: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid input.");
        }
    }

    private void start() {
        try (Connection connection = new Connection(new Socket(Server.IP, Server.PORT))) {
            this.connection = connection;
            System.out.println("Enter your message:");
            Thread writer = new Thread(new Writer());
            writer.start();
            Thread reader = new Thread(new Reader());
            reader.start();
            writer.join();
            System.out.println("Connection was closed.");
        } catch (IOException e) {
            System.out.println("Server not responding.");
        } catch (InterruptedException e) {
            System.out.println("Connection will be close.");
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Message incoming = (Message) connection.getInput().readObject();
                    System.out.println(incoming);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("You will be disconnected.");
                connection.close();
            }
        }
    }


    private class Writer implements Runnable {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String text = reader.readLine();
                    if (("/exit").equals(text)) {
                        connection.close();
                        Thread.currentThread().interrupt();
                        break;
                    }
                    Message outgoing = new Message(name, text);
                    outgoing.setSendingTime();
                    connection.getOutput().writeObject(outgoing);
                    connection.getOutput().flush();
                }
            } catch (IOException e) {
                Thread.currentThread().interrupt();
                connection.close();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setClientName();
        client.start();
    }
}
