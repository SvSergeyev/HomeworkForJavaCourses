package Homework.exam2;

import java.io.*;
import java.net.Socket;

public class Connection implements AutoCloseable {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private String sender;

    public Connection(Socket client) throws IOException {
        this.socket = client;
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
        /*
        try {
            while (!socket.isClosed()) {
                Message incoming = (Message) in.readObject();
                if (incoming.getText().equals("/exit")) {
                    System.out.println("Client initiated shutdown.");
                    out.writeObject(new Message("Server", "Your connection will be closed."));
                    break;
                }
                Server.messages.put(incoming);
            }
            System.out.println("Closing connection...");
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    @Override
    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
        System.out.println("Connection was closed.");
    }

   /* private class Reader implements Runnable {
        private final Connection connection;

        public Reader(Connection connection) {
            this.connection= connection;
        }

        @Override
        public void run() {
            try {
                while (!socket.isClosed()) {
                    Message incoming = (Message) in.readObject();
                    if (incoming.getText().equals("/exit")) {
                        System.out.println("Client initiated shutdown.");
                        out.writeObject(new Message("Server", "Your connection will be closed."));
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
                while (!socket.isClosed()) {
                    Message outgoing = Server.messages.take();
                    System.out.println("log: " + outgoing);
//                    for (Connection connection : Server.connections) {
                        out.writeObject(outgoing);
                        out.flush();
//                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }*/

//    public void sendMessage(Message message) {
//        try {
//            message.setSendingTime();
//            out.writeObject(message);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
