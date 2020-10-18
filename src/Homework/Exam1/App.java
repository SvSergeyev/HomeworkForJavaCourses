package Homework.Exam1;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Owner owner1 = new Owner("Ivan", "Ivanov", "01.01.1990");
        Owner owner11 = new Owner("Evan", "Evanov", "01.01.1999");
        Owner owner2 = new Owner("Pyotr", "Petrov", "02.02.1991");
        Owner owner3 = new Owner("Egor", "Egorov", "03.03.1992");
        Owner owner4 = new Owner("Alexey", "Vasiliev", "04.04.1993");
        Owner owner5 = new Owner("Vasily", "Lenin", "05.05.1994");

        Subscription subscription1 = new Subscription(owner1, 1, "one-time");
        Subscription subscription11 = new Subscription(owner11, 2, "one-time");
        Subscription subscription2 = new Subscription(owner2, 3, "day pass");
        Subscription subscription3 = new Subscription(owner3,6,"full");
        Subscription subscription4 = new Subscription(owner4, 9, "day pass");
        Subscription subscription5 = new Subscription(owner5, 12, "full");

        Fitness fitness = new Fitness(3);

        fitness.addSubscriptionToFitness(subscription1, "pool");
        fitness.addSubscriptionToFitness(subscription11, "gym");
        fitness.addSubscriptionToFitness(subscription2, "pool");
        fitness.addSubscriptionToFitness(subscription3, "group");
        fitness.addSubscriptionToFitness(subscription4, "gym");
        fitness.addSubscriptionToFitness(subscription5, "pool");

        System.out.println(fitness.toString());

        fitness.close();

    }
}
