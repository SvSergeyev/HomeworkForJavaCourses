package Homework.lesson23.task;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopTenWords {
    public static void main(String[] args) {
        // создать Map<String, Long> map
        // String - слово
        // Long - частота встречаемости
        // в мапу должны войти только первые 10 частоте встречаемости слов
        Map<String, Long> top10Words = new HashMap<>();
        try {
            top10Words = Files.lines(Paths.get("sources/task23.txt"))
                    .map(String::toLowerCase)
                    .flatMap(text -> Arrays.stream(text.split("[^a-zA-Zа-яА-Я]")))
                    .filter(word -> !word.equals(""))
                    .collect(Collectors.groupingBy(
                            Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .limit(10)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("10 WORDS: " + top10Words);

        // содержимое файла может быть любым
        // Map<String, Long> map = Files.lines(Paths.get("sources/task23.txt")) // читаем из файла в stream
        // методы вызывать по цепочке,
        // цепочку не разрывать (пока не получите результирующую мапу)

        // System.out.println(map);

    }
}




