package Homework.exam3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Menu {
    private static final HashMap<String, Command> commands = new HashMap<>();

//    public Menu() {
//        commands = new HashMap<>();
//    }

    public static void start() {
        System.out.println("[new]  - для начала новой игры");
        System.out.println("[load] - для загрузки предыдущих сохранений");
        System.out.println("[exit] - для выхода");
        commands.put("new", new StartCommand());
        commands.put("load", new LoadCommand());
        commands.put("exit", new ExitCommand());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action = reader.readLine().toLowerCase();
            commands.get(action.toLowerCase()).execute();
        } catch (IOException e) {
            System.out.println("Invalid input in menu/init");
        }
    }

    /*public void init() {
        StartCommand init = new StartCommand();
        init.execute();
    }

    public void load() {
        LoadCommand load = new LoadCommand();
        load.execute();
    }

    public void exit() {
        ExitCommand exit = new ExitCommand();
        exit.execute();
    }*/

}
