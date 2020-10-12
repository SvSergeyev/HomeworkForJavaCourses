package Homework.Lesson7;

public class Pupil extends Human implements StudAble {
    private int knowledge;
    private String subject;

    public void setSubject(String subject) {
        if (subject == null)
            throw new IllegalArgumentException("Ученику не задан предмет для изучения.");
        if (subject.trim().length() < 2)
            throw new IllegalArgumentException("Название предмета не может быть короче двух символов.");
        this.subject = subject;
    }
    public void setKnowledge(int knowledge) {
        if (knowledge < 0) throw new IllegalArgumentException("Уровень знаний не может быть меньше 0.");
        this.knowledge = knowledge;
    }

    public String getSubject() {
        return subject;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public Pupil(String name, int age, String subject, int knowledge) {
        super(name, age);
        setSubject(subject);
        setKnowledge(knowledge);
    }

    @Override
    public void study() {
        this.knowledge++;
    }

    @Override
    public String toString() {
        return "Ученик{" +
                "уровень знаний:" + knowledge +
                ", изучаемый предмет:'" + subject + '\'' +
                ", имя:'" + name + '\'' +
                ", возраст:" + age +
                '}';
    }
}
