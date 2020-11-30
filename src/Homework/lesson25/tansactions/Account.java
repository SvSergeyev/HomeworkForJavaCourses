package Homework.lesson25.tansactions;

import java.util.HashSet;

public class Account {
    private final long id;
    private double balance;
    private static HashSet<Long> ids = new HashSet<>();

    public Account(double balance) {
        this.id = getRandomID();
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public long getRandomID() {
        long tempID = (long) (Math.random() * 1_000_000);
        if (!ids.contains(tempID)) return tempID;
        else return getRandomID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        return Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
