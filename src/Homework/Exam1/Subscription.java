package Homework.Exam1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class Subscription {
   /* Каждый абонемент хранит:  дату регистрации (?текущая дата?)
                                дату окончания регистрации (проверка: дата окончания должна быть после даты регистрации).
    Каждый абонемент хранит информацию о владельце: имя,
                                                    фамилия,
                                                    год рождения.*/
    private final Owner owner;
    private final LocalDate expirationDate, registrationDate;
    private final String type;

    public String getType() {
        return type;
    }
    public Owner getOwner() {
        return owner;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Subscription(Owner owner, int durationInMonth, String type) {
        Objects.requireNonNull(owner, "Пользователь не задан.");
        Objects.requireNonNull(type, "Не указан тип посещаемых занятий.");
        this.owner = owner;
        this.registrationDate = LocalDate.now();
        if (durationInMonth <= 0) throw new IllegalArgumentException("Срок действия должен быть больше 0 месяцев.");
        this.expirationDate = registrationDate.plusMonths(durationInMonth);
        if (expirationDate.isBefore(registrationDate)) {
            throw new DateTimeException("Дата окончания регистрации не может предшествовать дате регистрации.");
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return owner.toString();
    }
}
