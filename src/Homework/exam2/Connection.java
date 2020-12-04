package Homework.exam2;

import java.io.*;
import java.net.Socket;

public class Connection implements AutoCloseable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void sendMessage(Message message) {
        try {
            message.setSendingTime();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message readMessage() throws IOException, ClassNotFoundException {
        return (Message) in.readObject();
    }

    @Override
    public void close() throws Exception {
        socket.close();
        in.close();
        out.close();
    }
}
