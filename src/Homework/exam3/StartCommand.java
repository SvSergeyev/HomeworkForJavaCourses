package Homework.exam3;

import java.util.Objects;

public class StartCommand implements Command {
    private Nodes startPoint;

    public StartCommand() {
    }

    public StartCommand(Nodes startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void execute() {
        Game game = new Game();
        game.init(Objects.requireNonNullElse(startPoint, Nodes.FOX));
    }
}
