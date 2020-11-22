package Homework.lesson22.Task2.compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparingExample {
    public static void main(String[] args) {

        // сортировать по значениям свойств: price, color, brand
        Car blackOpel = new Car("black", "opel", 2000);
        Car redOpel = new Car("red", "opel", 2500);
        Car yellowMazda = new Car("yellow", "mazda", 3000);
        Car greenMazda = new Car("green", "mazda", 3000);

        List<Car> cars = new ArrayList<>();
        cars.add(blackOpel);
        cars.add(redOpel);
        cars.add(yellowMazda);
        cars.add(greenMazda);

        System.out.println("Price:");
        cars.sort(Comparator.comparing(Car::getPrice));
        cars.forEach(System.out::println);

        System.out.println("Color: ");
        cars.sort(Comparator.comparing(Car::getColor));
        cars.forEach(System.out::println);

        System.out.println("Brand: ");
        cars.sort(Comparator.comparing(Car::getBrand));
        cars.forEach(System.out::println);
    }
}
