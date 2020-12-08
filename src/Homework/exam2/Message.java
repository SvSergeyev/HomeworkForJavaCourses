package Homework.exam2;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime sendingTime;
    private final String sender;
    private final String text;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public void setSendingTime() {
        this.sendingTime = LocalDateTime.now().withNano(0);
    }

    public String getSender() {
        return sender;
    }

    public static Message getMessage(String name, String text) {
        return new Message(name, text);
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return sendingTime + ": " + sender
                + " said: " + "\"" + text + "\"";
    }
}
