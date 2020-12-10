package Homework.exam2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;
    private Scanner scanner;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Введи имя:");
        String name = scanner.nextLine();
        String message;
        System.out.println("Введите сообщение");
        while (true){
            message = scanner.nextLine();
            sendAndPrintMessage(Message.getMessage(name, message));
        }
    }

    private void sendAndPrintMessage(Message message) {
        try (Connection connection
                     = new Connection(new Socket(ip, port))){
            connection.sendMessage(message);
            Message fromServer = connection.readMessage();
            System.out.println("от сервера: " + fromServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            new Client("127.0.0.1", 8090).start();
    }
}
