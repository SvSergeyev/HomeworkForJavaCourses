package Homework.exam2;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Message implements Serializable {
    private LocalDateTime sendingTime;
    private final String sender;
    private final String textOfMessage;
    private SimpleDateFormat timeFormat;

    public Message(String sender, String textOfMessage) {
        this.sender = sender;
        this.textOfMessage = textOfMessage;
        timeFormat = new SimpleDateFormat("MM.dd 'at' HH:mm:ss");
    }

    public void setSendingTime() {
        this.sendingTime = LocalDateTime.now().withNano(0);
    }

    public String getSender() {
        return sender;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    @Override
    public String toString() {
        DateFormat outputFormat = new SimpleDateFormat("dd MMMM 'в' HH:mm:ss");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String inputText = this.sendingTime.toString();

        Date date = null;
        try {
            date = inputFormat.parse(inputText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date) + " " + sender + " said: " + textOfMessage;
    }
}
