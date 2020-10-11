package Homework.Lesson7;

public class Pupil extends Human implements StudAble {
    private int knowledge;
    private String studyingSubject;

    public void setStudyingSubject(String studyingSubject) {
        if (studyingSubject == null) throw new IllegalArgumentException("Ученику не задан предмет для изучения.");
        if (studyingSubject.trim().length() < 2) throw new IllegalArgumentException("Название предмета не может быть короче двух символов.");
        this.studyingSubject = studyingSubject;
    }
    public void setKnowledge(int knowledge) {
        if (knowledge < 0) throw new IllegalArgumentException("Уровень знаний не может быть меньше 0.");
        this.knowledge = knowledge;
    }

    public String getStudyingSubject() {
        return studyingSubject;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public Pupil(String name, int age, String studyingSubject, int knowledge) {
        super(name, age);
        setStudyingSubject(studyingSubject);
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
                ", изучаемый предмет:'" + studyingSubject + '\'' +
                ", имя:'" + name + '\'' +
                ", возраст:" + age +
                '}';
    }
}
