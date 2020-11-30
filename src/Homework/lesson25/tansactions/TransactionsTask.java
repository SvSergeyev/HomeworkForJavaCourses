package Homework.lesson25.tansactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionsTask {
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            transactions.add(new Transaction(
                    getRandomAccount(),
                    getRandomAccount(),
                    Math.random() * 500));
        }
//        System.out.println("All transactions: " + transactions);

        for (Transaction transaction : transactions) {
            transaction.start();
        }

        for (Transaction transaction : transactions) {
            try {
                transaction.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static Account getRandomAccount() {
        return new Account(Math.random() * 1000);
    }

}