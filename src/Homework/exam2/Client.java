package Homework.exam2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
//    private Socket socket;
//    private ObjectOutputStream out;
//    private ObjectInputStream in;
    private final String sender;
    private Connection connection;

    public Client() {
        sender = "id" + (int) (Math.random() * 10000);
        try {
            connection = new Connection(new Socket(Server.IP, Server.PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread writerThread;
        Thread readerThread;
        try {
//            in = new ObjectInputStream(socket.getInputStream());
//            out = new ObjectOutputStream(socket.getOutputStream());
            writerThread = new Thread(new Writer());
            writerThread.start();
            readerThread = new Thread(new Reader());
            readerThread.start();
            System.out.println("Connection completed.");
            System.out.println("Your " + sender);
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                while (!connection.getSocket().isClosed()) {
                    Message incoming = (Message) connection.getIn().readObject();
                    if (!incoming.getSender().equals(sender)) {
                        System.out.println(incoming.toString());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Message reading error.");
            }
        }
    }


    private class Writer implements Runnable {

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Text your message here:");
            try {
                while (!connection.getSocket().isClosed()) {
                    String text = reader.readLine();
                    if (text.equals("/exit")) {
                        connection.close();
                        reader.close();
                        System.out.println("Your connection was closed.");
                    }
                    Message outgoing = new Message(sender, text);
                    outgoing.setSendingTime();
                    connection.getOut().writeObject(outgoing);
                    connection.getOut().flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

}
