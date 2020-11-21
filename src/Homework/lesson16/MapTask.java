package Homework.lesson16;

import java.util.*;

public class MapTask {
    public static void main(String[] args) {
        // TODO::
        //  написать статический метод, который принимает на вход:
        //  - Map firstTaskMap
        //  - String city
        //  и формирует List логинов, которые соответствуют
        //  переданному городу

        HashMap<String, String> firstTaskMap = new HashMap<>();
        firstTaskMap.put("qwe", "Тверь");
        firstTaskMap.put("asd", "Тверь");
        firstTaskMap.put("zxc", "Москва");
        firstTaskMap.put("rty", "Тверь");
        firstTaskMap.put("fgh", "Магадан");

        String city = "Тверь";
        System.out.println(loginsByCity(firstTaskMap, city));





        // TODO:: дан список слов (words).
        //  Написать метод, который будет возвращать количество
        //  одинаковых слов с списке
        //  в виде Map<String, Integer>,
        //  где String - слово и Integer - количество повторений

        List<String> words = new ArrayList<>();
        words.add("may");
        words.add("august");
        words.add("june");
        words.add("may");
        words.add("may");
        words.add("july");
        words.add("may");
        words.add("august");
        words.add("august");
        System.out.println(repetitionCounter(words));





        // TODO:: дана мапа (customerMap).
        //  Написать метод, который принимает на вход агрументы int from и int to и возвращает
        //  новую мапу, в которую войдут все покупатели,
        //  возраст которых находится в диапазоне [from, to)

        HashMap<String, Customer> customerMap = new HashMap<>();
        customerMap.put("1", new Customer("Павел", "1", 23));
        customerMap.put("2", new Customer("Олег", "2", 17));
        customerMap.put("3", new Customer("Максим", "3", 48));
        customerMap.put("4", new Customer("Евгения", "4", 67));
        System.out.println(customerFilter(customerMap,18, 35));


        // TODO:: Задания по тексту (text). На каждый пункт - минимум один метод:
        //  1. написать метод, принимающий на вход слово и возвращающий частоту
        //  встречаемости данного слова в тексте
        //  2. написать метод, который собирает все слова в группы
        //  по количеству букв:
        //  например, в одну группу попадут слова:
        //  3 - [the, war, jar, get, met...],
        //  в другую 2 - [on, up, no, of...],
        // Map<Integer, ArrayList>
        //  3. написать метод, который выводит в консоль топ 10 самых частых слов
        //  4. Вывести частоту встречаемости букв анг алфавита в данном тексте. Вывести в процентах для каждой буквы

        // в тексте содержатся только буквы и пробельные символы
        String text = "It is a uncover long established fact that a reader will be distracted uncover by the readable content of a page " +
                "when looking at its layout The point of using uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters" +
                "as uncover opposed to still using Content here humour uncover content here making it look like readable English Many desktop publishing " +
                "packages and web page editors now use Lorem Ipsum as their default model text and a search for lorem ipsum will " +
                "uncover many web sites still uncover in their infancy Various versions uncover have evolved over the years uncover sometimes by accident" +
                " sometimes on purpose injected humour and the like";

        String keyWord = "still";
        System.out.println("keyWord=" + keyWord +
                " is repeated=" + wordCounter(text, keyWord) + " times");
        System.out.println("Groping by length: " + wordGrouper(text));
        topTenWords(text);
        System.out.println("Letter frequency: ");
        letterFrequency(text);
//        Map<Integer, Set<String>> map = getGroups(text);

    }

    /**
     * Статический метод, принимающий на вход:
     * @param firstTaskMap - список городов и соответствующих им логинов
     * @param city - город, являющийся ключом для поиска
     * и возвращающий список логинов, соответствующих указанному городу:
     * @return List<String> logins</>
     */
    public static List<String> loginsByCity(Map<String, String> firstTaskMap, String city) {
        List<String> logins = new ArrayList<>();
        for (Map.Entry<String, String> pair: firstTaskMap.entrySet()) {
            try {
                if (city.equalsIgnoreCase(pair.getValue())) {
                    logins.add(pair.getKey());
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("param. \"city\" cannot be null");
                break;
            }
        }
        return logins;
    }

    /**
     * Статический метод, принимающий на вход:
     * @param words - список слов;
     * и возвращающий список слов с количеством их повторений:
     * @return Map<String, Integer> repetition </>
     * где String - слово, Integer - количество повторений.
     */
    public static Map<String, Integer> repetitionCounter(List<String> words) {
        Map<String, Integer> repetition = new HashMap<>();
        for (String word : words) {
            if (repetition.containsKey(word)) {
                repetition.replace(word, repetition.get(word) + 1);
            } else {
                repetition.put(word, 1);
            }
        }
        return repetition;
    }

    /**
     * Статический метод, получающий на входе:
     * @param customers - Map<String, Customer> список клиентов; </>
     * @param from - (int) возраст "ОТ" (включительно);
     * @param to - (int) возраст "ДО" (исключительно);
     * и возвращающий список клиентов, возраст которых находится
     *           в заданном диапазоне:
     * @return Map<String, Customer> filteredCustomerMap
     */
    public static Map<String, Customer> customerFilter(
            Map<String, Customer> customers,
            int from,
            int to) {
        Map<String, Customer> filteredCustomerMap = new HashMap<>();
        for (Map.Entry<String, Customer> pair: customers.entrySet()) {
            if (pair.getValue().getAge() >= from
                    && pair.getValue().getAge() < to)
                filteredCustomerMap.put(pair.getKey(), pair.getValue());
        }
        return filteredCustomerMap;
    }

    /**
     * Статический метод, принимающий на вход:
     * @param text - (String) исходный текст для поиска;
     * @param keyWord - (String) ключевое слово для поиска;
     * @return repetitionCounter.get(keyWord) - (int) количество повторений
     * в тексте ключевого слова
     */
    public static int wordCounter(String text, String keyWord) {
        Map<String, Integer> repetitionCounter = new HashMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            if (repetitionCounter.containsKey(word)) {
                repetitionCounter.replace(word, repetitionCounter.get(word) + 1);
            } else {
                repetitionCounter.put(word, 1);
            }
        }
        return repetitionCounter.get(keyWord);
    }


    /**
     * Статический метод, принимающий на вход:
     * @param text - (String) исходный текст для обработки;
     * @return Map<Integer, ArrayList<String>> groupsOfWords - map, в которой
     * слова сгруппированы в ArrayList<String> исходя из их длины (Integer)
     */
    public static Map<Integer, ArrayList<String>> wordGrouper(String text) {
        Map<Integer, ArrayList<String>> groupsOfWords = new HashMap<>();
        List<String> textAsList = Arrays.asList(
                text.toLowerCase().split(" "));

        for (String word : textAsList) {
            if (!groupsOfWords.containsKey(word.length())) {
                ArrayList<String> current = new ArrayList<>();
                current.add(word);
                groupsOfWords.put(word.length(), current);
            } else {
                groupsOfWords.get(word.length()).add(word);
            }
        }
        return groupsOfWords;
    }


    /**
     * Статический метод, принимающий на вход:
     * @param text - (String) текст для обработки.
     * Метод выводит 10 самых часто встречающихся в исходном тексте слов в порядке убывания.
     */
    public static void topTenWords(String text) {
        TreeSet<Map.Entry<String, Integer>> topTenSet = new TreeSet<>(new ValueComparator());
        Map<String, Integer> frequencyDictionary = new HashMap<>();
        List<String> textAsList = Arrays.asList(
                text.toLowerCase().split(" "));
        for (String word : textAsList) {
            if (frequencyDictionary.containsKey(word)) {
                frequencyDictionary.replace(
                        word, frequencyDictionary.get(word) + 1);
            } else {
                frequencyDictionary.put(word, 1);
            }
        }
        topTenSet.addAll(frequencyDictionary.entrySet());
        System.out.println("Top 10 words in source text: ");
        int count = 1;
        for (Map.Entry<String, Integer> entry : topTenSet) {
            if (count < 11) {
                System.out.println(count + ". " + entry.getKey());
                count++;
            }
        }
    }


    /**
     * Статический метод, выводящий в консоль частоту использования
     * каждой буквы латинского алфавита (в процентах от общего числа
     * используемых букв) в исходном тексте:
     * @param text - исходный текст
     */
    public static void letterFrequency(String text) {
        HashMap<Character, Double> letterContainer = new HashMap<>();
        HashMap<Character, Double> letterContainerWithWeights = new HashMap<>();
        char[] letters = text.replaceAll("[^a-zA-Z]", "").toLowerCase().toCharArray();
        for (char letter : letters) {
            if (letterContainer.containsKey(letter)) {
                letterContainer.replace(letter, letterContainer.get(letter) + 1);
            } else {
                letterContainer.put(letter, 1.0);
            }
        }
        for (Map.Entry<Character, Double> pair: letterContainer.entrySet()) {
            letterContainerWithWeights.put(pair.getKey(), (pair.getValue() / letters.length) * 100);
        }
        System.out.println(letterContainerWithWeights);
    }

/*
    public static HashMap<String, Customer> getByAge(HashMap<String, Customer> map, int from, int to) {
        HashMap<String, Customer> newMap = new HashMap<>();
        for (Map.Entry<String, Customer> entry: map.entrySet()) {
            if (entry.getValue().getAge() >= from &&
            entry.getValue().getAge() < to) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        return newMap;
    }
    public static List<String> getLogins(HashMap<String, String> map, String city) {
        List<String> strings = new ArrayList<>();
        for (Map.Entry<String, String> entry: map.entrySet()){
            if (entry.getValue().equals(city)) {
                strings.add(entry.getKey());
            }
        }
        return strings;
    }
    private static Map<Integer, Set<String>> getGroups(String text){
        Map<Integer, Set<String>> map = new TreeMap<>();
        String[] words = text.trim().toLowerCase().split(" ");
        for (String word : words) {
            Set<String> strings =
                    map.getOrDefault(word.length(), new HashSet<>());
            strings.add(word);
            map.put(words.length, strings);
        }
        return map;
    }
    private static void printTopTen(String text){
        String[] words = text.trim().toLowerCase().split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        TreeSet<Map.Entry<String, Integer>> entries = new TreeSet<>(new ValueComparator());
        entries.addAll(hashMap.entrySet());
    }
*/

}

class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o1.getValue().equals(o2.getValue())) return -1;
        return o2.getValue().compareTo(o1.getValue());
    }
}


