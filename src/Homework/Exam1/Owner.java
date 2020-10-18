package Homework.Exam1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Owner {
    private final String ownerName, ownerSurname;
    private final LocalDate dateOfBirth;

    public String getOwnerName() {
        return ownerName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }

    public Owner(String ownerName, String ownerSurname, String dateOfBirth) {
        Objects.requireNonNull(ownerName, "Имя посетителя не задано.");
        Objects.requireNonNull(ownerSurname, "Фамилия посетителя не задана.");
        Objects.requireNonNull(dateOfBirth, "Год рождения не задан.");
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
    }


    @Override
    public String toString() {
        return  ownerName + " " + ownerSurname;
    }
}
