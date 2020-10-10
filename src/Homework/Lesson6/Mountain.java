package Homework.Lesson6;

public class Mountain {
    // Гора создаётся с:
    // названием (не менее 4 символов),
    // страной (не менее 4 символов),
    // высотой (не менее 100 метров).

    private String name, country;
    private int height;

    public void setName(String name) {
        if (name != null && name.length() >= 4) this.name = name;
        else throw new IllegalArgumentException("Название горы не может быть короче четырех символов.");
    }

    public void setCountry(String country) {
        if (country != null && country.length() >= 4) this.country = country;
        else throw new IllegalArgumentException("Название страны не может быть короче четырех символов.");
    }

    public void setHeight(int height) {
        if (height >= 100) this.height = height;
        else throw new IllegalArgumentException("Высота не может быть меньше 100 метров.");
    }

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
