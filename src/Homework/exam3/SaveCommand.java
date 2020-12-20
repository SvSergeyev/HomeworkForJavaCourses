package Homework.exam3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveCommand implements Command {
    private final Nodes node;
    private String name;

    public SaveCommand(Nodes node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        name = "save_" + now.format(formatter);
        File saveFile = new File("src/Homework/exam3/savedGames/" + name + ".bin");
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            output.writeObject(node);
            System.out.println("Сохранено под именем " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
