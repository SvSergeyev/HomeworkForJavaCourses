package Homework.Lesson7;

public class Teacher extends Human implements TeachAble {
    private String subjectTaught;

    public void setSubjectTaught(String subjectTaught) {
        if (subjectTaught == null) throw new IllegalArgumentException("Ученику не задан предмет для изучения.");
        if (subjectTaught.trim().length() < 2) throw new IllegalArgumentException("Название предмета не может быть короче двух символов.");
        this.subjectTaught = subjectTaught;
    }
    public String getSubjectTaught() {
        return subjectTaught;
    }

    public Teacher(String name, int age, String subjectTaught) {
        super(name, age);
        setSubjectTaught(subjectTaught);
    }

    @Override
    public void teach(StudAble pupil) {
        pupil.study();
    }

    @Override
    public String toString() {
        return "Учитель{" +
                "Преподаваемый предмет:'" + subjectTaught + '\'' +
                ", имя:'" + name + '\'' +
                ", возраст:" + age +
                '}';
    }
}
