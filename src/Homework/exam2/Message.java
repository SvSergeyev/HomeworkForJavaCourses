package Homework.exam2;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime sendingTime;
    private String sender;
    private String text;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public void setSendingTime() {
        this.sendingTime = LocalDateTime.now().withNano(0);
    }

    public static Message getMessage(String name, String text) {
        return new Message(name, text);
    }

    @Override
    public String toString() {
        return sender + " said: "
                + "\"" + text + "\"" +
                " at: " + sendingTime;
//        return "Message{" +
//                "sendingTime=" + sendingTime +
//                ", sender=" + sender +
//                ", text='" + text + '\'' +
//                '}';
    }
}
