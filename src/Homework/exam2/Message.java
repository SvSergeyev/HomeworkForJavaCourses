package Homework.exam2;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime sendingTime;
    private final String nameOfSender;
    private final String textOfMessage;

    public Message(String nameOfSender, String textOfMessage) {
        this.nameOfSender = nameOfSender;
        this.textOfMessage = textOfMessage;
    }

    public void setSendingTime() {
        this.sendingTime = LocalDateTime.now().withNano(0);
    }

    public String getNameOfSender() {
        return nameOfSender;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    @Override
    public String toString() {
        return sendingTime + ":" + nameOfSender + " said: " + textOfMessage;
    }
}
