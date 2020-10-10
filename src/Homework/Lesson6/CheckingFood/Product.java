package Homework.Lesson6.CheckingFood;

public class Product {
    private String name;
    private double proteins, fats, carbohydrates;
    private int calories;

    public void setName(String name) {
        if (name != null) this.name = name;
        else throw new IllegalArgumentException("Не задано название продукта.");
    }
    public void setProteins(double proteins) {
        if (proteins >= 0) this.proteins = proteins;
        else throw new IllegalArgumentException("Количество белка - положительное число");
    }
    public void setFats(double fats) {
        if (fats >= 0) this.fats = fats;
        else throw new IllegalArgumentException("Количество жиров - положительное число");
    }
    public void setCarbohydrates(double carbohydrates) {
        if (carbohydrates >= 0) this.carbohydrates = carbohydrates;
        else throw new IllegalArgumentException("Количество углеводов - положительное число");
    }
    public void setCalories(int calories) {
        if (calories >= 0) this.calories = calories;
        else throw new IllegalArgumentException("Количество калорий - положительное число");
    }

    public String getName() {
        return name;
    }
    public double getProteins() {
        return proteins;
    }
    public double getFats() {
        return fats;
    }
    public double getCarbohydrates() {
        return carbohydrates;
    }
    public int getCalories() {
        return calories;
    }

    public Product() {

    }
    public Product(String name, double proteins, double fats, double carbohydrates, int calories) {
        setName(name);
        setProteins(proteins);
        setFats(fats);
        setCarbohydrates(carbohydrates);
        setCalories(calories);
    }
    public Product(String name, int calories) {
        setName(name);
        setCalories(calories);
    }
    public Product(double proteins, double fats, double carbohydrates, int calories) {
        setProteins(proteins);
        setFats(fats);
        setCarbohydrates(carbohydrates);
        setCalories(calories);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                '}';
    }
}
