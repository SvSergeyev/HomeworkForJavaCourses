package Homework.lesson23.task.pupils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Pupil {
    enum Gender {
        MALE, FEMALE
    }
    private int number; // уникальное значение для каждого ученика
    private String name;
    private Gender gender;
    private LocalDate birth;


    public Pupil(int number, String name, Gender gender, LocalDate birth) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(this.birth, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                '}';
    }

    public static LocalDate getRandomBirth() {
        Random random = new Random();
        int start = (int) LocalDate.of(1990, 1, 1).toEpochDay();
        int end = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long randomDay = start + random.nextInt(end - start);
        return LocalDate.ofEpochDay(randomDay);
    }
}