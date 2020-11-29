package Homework.lesson25;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreadCounter extends Thread {
    private final List<String> partOfText;
    private final int sizeOfTop;
    private Map<String, Long> subtotal = new LinkedHashMap<>();
    private static final Map<String, Long> summaryMap = new LinkedHashMap<>();

    public ThreadCounter(List<String> partOfText,
                         int sizeOfTop) {
        this.partOfText = partOfText;
        this.sizeOfTop = sizeOfTop;
    }

    public static Map<String, Long> getSummaryMap() {
        return summaryMap;
    }

    private Map<String, Long> getSubtotalMap() {
        subtotal = partOfText.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((word1, word2) -> word2.getValue().compareTo(word1.getValue()))
                .limit(sizeOfTop)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
//        System.out.println(subtotal);
        passToTheSummaryMap();
        return subtotal;
    }

    private Map<String, Long> passToTheSummaryMap () {
        synchronized (summaryMap) {
            for (String word : subtotal.keySet()) {
                summaryMap.put(word,
                        summaryMap.getOrDefault(word,0L) +
                                subtotal.get(word));
            }
        }
        return summaryMap;
    }

    @Override
    public void run() {
        getSubtotalMap();
    }
}
