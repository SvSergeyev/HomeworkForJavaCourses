package Homework.Lesson5;

import java.util.Arrays;
import java.util.Scanner;

public class Lesson5 {
    public static void main(String[] args) {
        fillingTheArray();
//        searchForOccurrences();
//        palindromeCheck();
//        caseCorrector();
//        longestWord();
    }

    public static void fillingTheArray() {
        /*
        Задать массив на N слов.
        В цикле считывать с консоли слова (scanner.nextLine()), и добавлять их в массив
        (добавлять новое слово в массив можно только если в нем его еще нет).
        В итоге в массиве будут только уникальные слова.
        Выход из программы по слову exit (его в массив не добавлять) или если массив заполнен
        Перед завершением программы вывести получившийся массив в консоль.
        */
        String[] strings = new String[5];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добавь первое слово: ");
        strings[0] = scanner.nextLine();
        int i = 1;
        while (i < strings.length) {
            int duplicate = 0;
            System.out.println("Добавь следующее слово: ");
            String word = scanner.nextLine();
            if (word.equals("exit")) break;
            for (int s = 0; s < strings.length; s++) {
                if (!word.equals(strings[s])) {
                    ++s;
                } else duplicate++;
                if (duplicate == 0) strings[i] = word;
            }
            i++;
        }
        System.out.println(Arrays.toString(strings));
    }

    public static void searchForOccurrences() {
        /*
        Найти количество вхождений одной строки в другую.
        Например, строка "дом" встречается в строке "дом домик домой одомашненный" 4 раза
        */
        String keyString = "дом";
        String sourceString = "дом домик домой одомашненный";
        int countOfOccurrences =
                (sourceString.length() - sourceString.replace(keyString, "").length()) / keyString.length();
        System.out.println(countOfOccurrences);
    }

    public static void palindromeCheck() {
        /*
        Написать функцию, которая проверяет, является ли строка палиндромом.
        */
        System.out.println("Введи текст для проверки: ");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        StringBuilder reverseString = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            reverseString.append(string.charAt(i)); // .substring(i, i + 1)
        }
        if ((string.replace(" ", "").toLowerCase())
                .equals(reverseString.toString().replace(" ", "").toLowerCase())) {
            System.out.println("Поздравляю, у вас палиндром.");
        } else System.out.println("Это был не палиндром");
    }

    public static void caseCorrector() {
        /*
        Заменить все буквы в слове на строчные, а первую букву на заглавную
        Например, дано hello, получаем Hello / дано HeLLO, получаем Hello
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи текст: ");
        String sourceString = scanner.nextLine();
        System.out.println( sourceString.substring(0, 1).toUpperCase() +
                sourceString.substring(1).toLowerCase());
    }

    public static void longestWord() {
        /*
        Найдите самое длинное слово в предложении при условии, что в предложении все слова разной длины.
        */
        System.out.println("Введи предложение: ");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        string = string.replace(",", ""); // игнорируем запятую, если есть

        String[] strings = string.split(" ");
        int max = strings[0].length();
        String longestWord = strings[0];
        for (String s : strings) {
            if (s.length() > max) {
                max = s.length();
                longestWord = s;
            }
        }
        System.out.println("Самое длинное слово в предложении - \"" + longestWord + "\", " + max + " знаков.");
    }
}
