package Homework.exam3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoadCommand implements Command {

    @Override
    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File[] filesArr = new File("src/Homework/exam3/savedGames").listFiles();
        HashMap<Integer, File> filesMap = new HashMap<>();
        for (int i = 0; i < filesArr.length; i++) {
            filesMap.put(i + 1, filesArr[i]);
        }

        System.out.println("Доступные сохранения:\n[#] для выбора");
        for (Map.Entry<Integer, File> entry : filesMap.entrySet()) {
            System.out.println(entry.getKey() + ". " +
                    entry.getValue()
                    .getName()
                    .replace(".bin", ""));
        }

        try {
            int choice = Integer.parseInt(reader.readLine());
            for (Map.Entry<Integer, File> entry : filesMap.entrySet()) {
                if (entry.getKey().equals(choice)) {
                    try (ObjectInputStream input =
                                 new ObjectInputStream(new FileInputStream(entry.getValue()))) {
                        Nodes startPoint = (Nodes) input.readObject();
                        StartCommand start = new StartCommand(startPoint);
                        start.execute();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Введи номер сохранения");
        }
    }
}
