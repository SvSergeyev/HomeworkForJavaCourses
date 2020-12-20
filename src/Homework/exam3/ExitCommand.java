package Homework.exam3;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Спасибо за игру");
    }
}
