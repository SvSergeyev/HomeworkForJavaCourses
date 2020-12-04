package Homework.exam2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private final String ip;
    private final int port;
    BufferedReader reader;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws Exception {
        System.out.println("enter your name: ");
        String name = reader.readLine();
        System.out.println("text here: ");
        String sentence;
        while (true) {
            sentence = reader.readLine();
            sendMessage(Message.getMessage(name, sentence));
        }
    }

    public void sendMessage (Message message) throws Exception {
        try (Connection connection = new Connection(new Socket(ip, port))) {
            connection.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        try {
            new Client("localhost", 8090).start();
        } catch (Exception e) {
            System.out.println("client connection error...");
            e.printStackTrace();
        }
    }

}
