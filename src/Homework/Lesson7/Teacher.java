package Homework.Lesson7;

public class Teacher extends Human implements TeachAble {
    private String subject;

    public void setSubject(String subject) {
        if (subject == null) throw new IllegalArgumentException("Ученику не задан предмет для изучения.");
        if (subject.trim().length() < 2) throw new IllegalArgumentException("Название предмета не может быть короче двух символов.");
        this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }

    public Teacher(String name, int age, String subject) {
        super(name, age);
        setSubject(subject);
    }

    @Override
    public void teach(StudAble pupil) {
        pupil.study();
    }

    @Override
    public String toString() {
        return "Учитель{" +
                "Преподаваемый предмет:'" + subject + '\'' +
                ", имя:'" + name + '\'' +
                ", возраст:" + age +
                '}';
    }
}
