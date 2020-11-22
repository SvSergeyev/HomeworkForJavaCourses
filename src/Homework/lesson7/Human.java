package Homework.lesson7;

abstract public class Human {
    protected final String name;
    protected int age;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public Human(String name, int age) {
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException("Имя не может быть короче 2 символов.");
        if (age < 7) throw new IllegalArgumentException("Возраст не может быть меньше 7 лет.");
        this.name = name;
        this.age = age;
    }

}
