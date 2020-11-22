package Homework.lesson15.compare;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparingExample {
    public static void main(String[] args) {

        // сортировать по значениям свойств: price, color, brand
        Car blackOpel = new Car("green", "opel", 4000);     // "black", "opel", 2000
        Car redOpel = new Car("red", "mazda", 2500);        // "red", "opel", 2500
        Car yellowMazda = new Car("yellow", "mazda", 4000); // "yellow", "mazda", 3000
        Car greenMazda = new Car("red", "opel", 3000);      // "green", "mazda", 3000

        Comparator<Car> carPriceComparator = new PriceComparator()
                .thenComparing(new ColourComparator())
                .thenComparing(new BrandComparator());
        TreeSet<Car> carTreeSet = new TreeSet<>(carPriceComparator);
        carTreeSet.add(blackOpel);
        carTreeSet.add(redOpel);
        carTreeSet.add(yellowMazda);
        carTreeSet.add(greenMazda);



        for (Car car : carTreeSet) {
            System.out.println(car.toString());
        }
    }
}
