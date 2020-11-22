package Homework.lesson22.Task1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class DifferentImplementations {
    /*
    * 1. Отработать написание лямбда (написать по две реализации) для:
    *   - Predicate;
    *   - Function;
    *   - UnaryOperator;
    *   - BinaryOperator;
    *   - Consumer.
    * */

    public static void main(String[] args) {
        Predicate<String> contains = text -> text.toLowerCase().contains("ex");
        Predicate<String> notNull= Objects::nonNull;
        String s = "Test text";
        System.out.println("Predicate implementation: "
                + contains.and(notNull).test(s));

        String number = "258";
        Function<String, Integer> stringToInteger = Integer::valueOf;
        System.out.println(stringToInteger.apply(number));


    }
}
