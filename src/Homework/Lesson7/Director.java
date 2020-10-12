package Homework.Lesson7;

final public class Director extends Human implements TeachAble {
    private boolean isWork = true;

    public Director(String name, int age) {
        super(name, age);
    }

    public void startLearning() {
        if(!isWork) {
            isWork = true;
            System.out.println("Начат новый учебный день.");
        } else System.out.println("Школа уже открыта, идет процесс обучения.");
    }

    public void finishLearning() {
        if(isWork) {
            isWork = false;
            System.out.println("Учебный день завершен.");
        } else System.out.println("Школа уже закрыта, процесс обучения не идет.");
    }

    @Override
    public void teach(StudAble pupil) {
        pupil.study();
    }

    @Override
    public String toString() {
        return "Директор{" +
                "Имя:'" + name + '\'' +
                ", возраст:" + age +
                '}';
    }
}
