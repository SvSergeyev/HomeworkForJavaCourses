package Homework.lesson25.tansactions;

import java.util.Objects;
import java.util.UUID;

public class Transaction extends Thread {
    private final String uuid;
    private final double amount;
    private final Account fromAccount;
    private final Account toAccount;

    public Transaction(Account fromAccount, Account toAccount, double amount) {
        if (amount < 0) throw new IllegalArgumentException("\"amount\" can only be positive...");
        else this.amount = amount;
        Objects.requireNonNull(this.fromAccount = fromAccount, "\"from\" is null...");
        Objects.requireNonNull(this.toAccount = toAccount, "\"to\" is null...");
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        if (!fromAccount.equals(toAccount)) {
            synchronized (fromAccount) {
                if (fromAccount.getBalance() > amount) {
                    synchronized (toAccount) {
                        toAccount.setBalance(toAccount.getBalance() + amount);
                        System.out.println("Выполнен перевод в размере " + amount
                                + " с аккаунта " + fromAccount.getId()
                                + " на аккаунт " + toAccount.getId());
                    }
                } else {
                    System.out.println(
                            "Транзакция " + uuid + ": недостаточно средств для совершения операции.");
                }
            }
        } else {
            System.out.println("Транзакция " + uuid + ": недопустимая операция.");
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid='" + uuid + '\'' +
                ", amount=" + amount +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                '}';
    }
}