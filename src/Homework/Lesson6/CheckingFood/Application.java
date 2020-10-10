package Homework.Lesson6.CheckingFood;

public class Application {
    public static void main(String[] args) {
        Product dumplings = new Product();
        Product beef = new Product("beef", 26, 15, 0, 250);

        Product bread = new Product("wheat bread",265);
        Product peach = new Product(0.9, 0.3, 10, 39);

        MyFood diet = new MyFood(1, 100, 100,100,500);
        MyFood diet2 = new MyFood(4, 10, 20, 10, 100);

        diet.addProduct(beef);
        diet2.addProduct(dumplings, beef, bread, peach);

        diet.getPermittedFood();
        diet2.getPermittedFood();

    }
}
