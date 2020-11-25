package Homework.lesson23.task.pupils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PupilTask {
    public static void main(String[] args) {
        // обращение к enum Gender через имя класса - Pupil.Gender
        ArrayList<Pupil> pupils = new ArrayList<>();
        pupils.add(new Pupil(100_000, "Sergey", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_008, "Sergey", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_001, "Elena", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_002, "Anna", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_003, "Igor", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_004, "Ivan", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_005, "Alexander", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_006, "Irina", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_007, "Irina", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));

        // Используя Stream API:

        // 1. Разделить учеников на две группы: мальчиков и девочек.
        // Результат: Map<Pupil.Gender, ArrayList<Pupil>>
        Map<Pupil.Gender, ArrayList<Pupil>> byGender = pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender,
                        Collectors.toCollection(ArrayList::new)));
        System.out.println(byGender);

        // 2. Найти средний возраст учеников
        double averageAge = pupils.stream().mapToInt(Pupil::getAge).average().getAsDouble();
        System.out.printf("%.1f", averageAge);
        System.out.println();

        // 3. Найти самого младшего ученика
        Pupil youngestPupil = pupils.stream()
                .min(Comparator.comparing(Pupil::getAge)).get();
        System.out.println(youngestPupil);

        // 4. Найти самого старшего ученика
        Pupil oldestPupil = pupils.stream()
                .max(Comparator.comparing(Pupil::getAge)).get();
        System.out.println(oldestPupil);

        // 5. Собрать учеников в группы по году рождения
        Map<Integer, List<Pupil>> byYearOfBirth = pupils.stream()
                .collect(Collectors.groupingBy(year -> year.getBirth().getYear()));
        System.out.println(byYearOfBirth);

        // 6. Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль
        ArrayList<Pupil> uniqueNames = pupils.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(Pupil::getName))),
                        ArrayList::new));
        System.out.println(uniqueNames);

        // 7. Отсортировать по полу, потом по дате рождения, потом по имени (в обратном порядке), собрать в список (List)
        List<Pupil> sorted = pupils.stream()
                .sorted(Comparator.comparing(Pupil::getGender)
                        .thenComparing(Pupil::getBirth)
                        .thenComparing(Pupil::getName))
                .collect(Collectors.toList());
        System.out.println("last: " + sorted);

        // 8. Вывести в консоль всех учеников в возрасте от N до M лет


        // 9. Собрать в список всех учеников с именем=someName

        // 10. Собрать Map<Pupil.Gender, Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола
    }
}
