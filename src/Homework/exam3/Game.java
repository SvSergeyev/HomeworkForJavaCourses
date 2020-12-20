package Homework.exam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Game {
    private static HashMap<Nodes, PlotFork> allNodes;
    private Nodes startPoint;

    public Game() {
        allNodes = new HashMap<>();
        allNodes.put(Nodes.FOX, new PlotFork(Nodes.BACK_TO_HOME, Nodes.GO_IN_SEARCH));
        allNodes.put(Nodes.BACK_TO_HOME, new PlotFork());
        allNodes.put(Nodes.GO_IN_SEARCH, new PlotFork(Nodes.TRY_TO_FIND_OUT, Nodes.SEARCH_ALONE));
        allNodes.put(Nodes.TRY_TO_FIND_OUT, new PlotFork(Nodes.ASK_THE_OWL, Nodes.ASK_THE_WOLF));
        allNodes.put(Nodes.SEARCH_ALONE, new PlotFork());
        allNodes.put(Nodes.ASK_THE_OWL, new PlotFork(Nodes.BELIEVE_THE_OWL, Nodes.SEARCH_ALONE));
        allNodes.put(Nodes.ASK_THE_WOLF, new PlotFork(Nodes.BACK_TO_HOME, Nodes.SEARCH_ALONE));
        allNodes.put(Nodes.BELIEVE_THE_OWL, new PlotFork(Nodes.SEARCH_ALONE, Nodes.GET_HONEY));
        allNodes.put(Nodes.GET_HONEY, new PlotFork(Nodes.WAIT, Nodes.STEAL_HONEY));
        allNodes.put(Nodes.WAIT, new PlotFork(Nodes.EAT_AND_REST, Nodes.CARRY_HONEY));
        allNodes.put(Nodes.STEAL_HONEY, new PlotFork());
        allNodes.put(Nodes.EAT_AND_REST, new PlotFork());
        allNodes.put(Nodes.CARRY_HONEY, new PlotFork(Nodes.SEARCH_ALONE, Nodes.BACK_TO_HOME));
    }


    public void init(Nodes startPoint) {
        Objects.requireNonNull(startPoint, "Не задана точка начала");
        if (startPoint.equals(Nodes.FOX)) {
            System.out.println("Начало новой игры");
        } else {
            System.out.println("Продолжаем игру");
        }
        System.out.println("[1][2] - для выбора действия");
        System.out.println("[save] - для сохранения");
        try {
            begin(startPoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void begin(Nodes startPoint) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Nodes currentPosition = startPoint;
        PlotFork currentFork = Game.allNodes.get(currentPosition);

        // first iteration
        System.out.println(currentPosition.getText());

        while (currentFork.getFirstAnswer() != null || currentFork.getSecondAnswer() != null) {
            String choice = reader.readLine();
            if (choice.equals("1")) {
                if (currentFork.getFirstAnswer() != null) {
                    currentPosition = currentFork.getFirstAnswer();
                    currentFork = Game.allNodes.get(currentPosition);
                    System.out.println(currentPosition.getText());
                } else break;
            } else if (choice.equals("2")) {
                if (currentFork.getSecondAnswer() != null) {
                    currentPosition = currentFork.getSecondAnswer();
                    currentFork = Game.allNodes.get(currentPosition);
                    System.out.println(currentPosition.getText());
                } else break;
            } else if (choice.equalsIgnoreCase("save")) {
                new SaveCommand(currentPosition).execute();
            } else if (choice.equalsIgnoreCase("exit")) {
                new ExitCommand().execute();
                break;
            } else break;
        }
    }


    private static class PlotFork {
        private Nodes firstAnswer;
        private Nodes secondAnswer;

        public PlotFork() {

        }

        public PlotFork(Nodes firstAnswer, Nodes secondAnswer) {
            this.firstAnswer = firstAnswer;
            this.secondAnswer = secondAnswer;
        }

        public Nodes getFirstAnswer() {
            return firstAnswer;
        }

        public Nodes getSecondAnswer() {
            return secondAnswer;
        }
    }
}


