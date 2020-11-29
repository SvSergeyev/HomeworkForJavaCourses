package Homework.lesson25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/*
Основной поток:
    читает из файла текст;
    разбивает его на несколько частей.

Каждый поток:
    получает свою часть;
    считает количество слов;
    переносит результат в общую Map<String, Long>.
Основной поток выводит результат: топ N слов
*/
public class TopNWords {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors()); // 4
        int maxNumberOfThreads = Runtime.getRuntime().availableProcessors();
        ArrayList<String> sourceText = new ArrayList<>();
        int sizeOfTop = getSizeOfTop();
        ArrayList<ThreadCounter> threadCounters = new ArrayList<>();
        try {
            sourceText = Files.lines(
                    Paths.get("sources/task23.txt"))
                    .map(String::toLowerCase)
                    .flatMap(text -> Arrays.stream(
                            text.split("[^а-яА-Я]")))
                    .filter(word -> !word.equals(""))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("source: " + sourceText);

        List<List<String>> substrings = new ArrayList<>();

        int stepSize = sourceText.size() / maxNumberOfThreads; // 57

        int start = 0;
        int end = stepSize;
        for (int i = 0; i < maxNumberOfThreads; i++) {
            int reminder = sourceText.subList(end, sourceText.size()).size();

            if (reminder > sourceText.subList(start, end).size()) {
                substrings.add(sourceText.subList(start, end));
                threadCounters.add(new ThreadCounter(
                        substrings.get(i), sizeOfTop));
                start = end + 1;
                end += stepSize;
            } else {
                end += reminder;
                substrings.add(sourceText.subList(start, end));
            }
        }
//        System.out.println("get 0 " + substrings.get(0));

        for (ThreadCounter counter : threadCounters) {
            counter.start();
        }

        for (ThreadCounter counter : threadCounters) {
            try {
                counter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TOTAL: " + ThreadCounter.getSummaryMap().entrySet().stream()
                .sorted((word1, word2) -> word2.getValue().compareTo(word1.getValue()))
                .limit(sizeOfTop)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue)));

    }

    public static int getSizeOfTop() {
        System.out.println("Задай размер результирующего списка: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size;
        try {
            size = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return size;
    }
}
