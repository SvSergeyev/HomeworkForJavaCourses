package Homework.lesson23.task.tansactions;

public class Account {
    private String number;
    private long balance;

    public Account(String number, long balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }
}
