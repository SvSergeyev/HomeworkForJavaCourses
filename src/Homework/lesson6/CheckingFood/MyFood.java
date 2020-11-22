package Homework.lesson6.CheckingFood;

public class MyFood {

    private double proteins, fats, carbohydrates;
    private int calories, listSize, count;
    protected Product[] permittedFood;
    private boolean foodCheckFlag;

    public void setListSize(int numberOfProducts) {
        if (numberOfProducts < 1) throw new IllegalArgumentException("Количество продуктов не может быть меньше 1.");
        else listSize = numberOfProducts;
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

    public MyFood(int numberOfProducts, double proteins, double fats,
                  double carbohydrates, int calories) {
        setListSize(numberOfProducts);
        setProteins(proteins);
        setFats(fats);
        setCarbohydrates(carbohydrates);
        setCalories(calories);
        permittedFood = new Product[listSize];
    }

    public boolean goodFoodCheck(Product product) {
        String failureMsg = "Продукт " + product.getName() + " не добавлен в список, т.к.: ";
        if (product.getProteins() > this.proteins) failureMsg += "количество белков превышает норму; ";
        if (product.getFats() > this.fats) failureMsg += "количество жиров превышает норму; ";
        if (product.getCarbohydrates() > this.carbohydrates) failureMsg += "количество углеводов превышает норму; ";
        if (product.getCalories() > this.calories) failureMsg += "количество калорий превышает норму; ";
        if (!failureMsg.equals("Продукт " + product.getName() + " не добавлен в список, т.к.: ")) System.out.println(failureMsg);
        else foodCheckFlag = true;
        return foodCheckFlag;
    }

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Не задан продукт для проверки.");
        if (count == permittedFood.length) System.out.println("Список заполнен.");
        if (goodFoodCheck(product)) {
            permittedFood[count++] = product;
        }
    }

    public void addProduct(Product... products) {
        for (Product product : products) {
            if (product != null && goodFoodCheck(product)) {
                if (count == permittedFood.length) {
                    System.out.println("Список заполнен, некоторые продукты могли быть не добавлены.");
                    return;
                }
                permittedFood[count++] = product;
            }
            else throw new IllegalArgumentException("Не задан продукт для проверки.");
        }
    }

    public void getPermittedFood() {
        System.out.println("В список добавлены следующие продукты: ");
        for (Product product : permittedFood) {
            if (product != null) System.out.println(product.toString());
        }
        System.out.println();
    }

}
