package Homework.Lesson6;

public class Mountain {
    // Гора создаётся с названием (не менее 4 символов), страной (не менее 4 символов) и высотой (не менее 100 метров)
    // поля класса:
    private String name, country;
    private int height;

    // гет/сет:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 4)
            throw new IllegalArgumentException("Название горы не может быть короче 4-х символов");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().length() < 3)
            throw new IllegalArgumentException("Название страны не может быть короче 3-х символов"); // а как же США и ЮАР?
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 100)
            throw new IllegalArgumentException("Высота не может быть меньше 100 метров");
        this.height = height;
    }

    // конструкторы:
    public Mountain() {
        name = "";
        country = "";
        height = -1;
    }

    public Mountain(String name, int height) {

    }
    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    // методы:

    // переопределенные методы:
}
