package Homework.lesson23.task.pupils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class PupilTask {
    public static void main(String[] args) {
        // обращение к enum Gender через имя класса - Pupil.Gender
        ArrayList<Pupil> pupils = new ArrayList<>();
        pupils.add(new Pupil(100_000, "Sergey", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_001, "Elena", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_002, "Anna", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_003, "Igor", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_004, "Ivan", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_005, "Alexander", Pupil.Gender.MALE, Pupil.getRandomBirth()));
        pupils.add(new Pupil(100_006, "Irina", Pupil.Gender.FEMALE, Pupil.getRandomBirth()));

        // Используя Stream API:

        // 1. Разделить учеников на две группы: мальчиков и девочек.
        // Результат: Map<Pupil.Gender, ArrayList<Pupil>>
        Map<Pupil.Gender, ArrayList<Pupil>> byGender = pupils.stream()
                .collect(Collectors.groupingBy(Pupil::getGender,
                        Collectors.toCollection(ArrayList::new)));
        System.out.println(byGender);

        // 2. Найти средний возраст учеников
        double averageAge = pupils.stream()
                .mapToDouble(Pupil::getAge).average().orElseGet(null);
        System.out.printf("%.1f", averageAge);
        System.out.println();

        // 3. Найти самого младшего ученика
        Pupil youngestPupil = pupils.stream()
                .min(Comparator.comparing(Pupil::getAge)).orElseGet(null);
        System.out.println(youngestPupil);

        // 4. Найти самого старшего ученика
        Pupil oldestPupil = pupils.stream()
                .max(Comparator.comparing(Pupil::getAge)).orElseGet(null);
        System.out.println(oldestPupil);

        // 5. Собрать учеников в группы по году рождения


        // 6. Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль

        // 7. Отсортировать по полу, потом по дате рождения, потом по имени (в обратном порядке), собрать в список (List)

        // 8. Вывести в консоль всех учеников в возрасте от N до M лет

        // 9. Собрать в список всех учеников с именем=someName

        // 10. Собрать Map<Pupil.Gender, Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола
    }
}
