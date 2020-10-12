package Homework.Lesson7;

import java.util.Arrays;

public class School {
    private final String schoolName;
    private Director director;
    private Teacher[] teachers;
    private Pupil[] pupils;
    private int countT, countP;

    public void setDirector(Director director) {
        if (director != null) this.director = director;
        else throw new IllegalArgumentException("Школа не может функционировать без директора.");
    }

    public School(String schoolName, int numberOfTeachers, int numberOfPupils, Director director) {
        this.schoolName = schoolName;
        if (numberOfTeachers > 0) teachers = new Teacher[numberOfTeachers];
        else throw new IllegalArgumentException("Количество учителей должно быть больше 0.");
        if (numberOfPupils > 0) pupils = new Pupil[numberOfPupils];
        else throw new IllegalArgumentException("Количество учеников должно быть больше 0.");
        setDirector(director);
    }

    public void addTeacher(Teacher... teachers) {
        for (Teacher teacher : teachers) {
            if (teacher != null && countT < teachers.length) {
                this.teachers[countT++] = teacher;
            } else if (countT == teachers.length) throw new IllegalArgumentException("В школе уже максимальное количество учителей.");
            else throw new IllegalArgumentException("Не указан учитель для добавления в список учителей.");
        }
    }

    public void addPupil(Pupil... pupils) {
        for (Pupil pupil : pupils) {
            if (pupil != null && countP < pupils.length) {
                this.pupils[countP++] = pupil;
            } else if (countP == pupils.length) throw new IllegalArgumentException("В школе уже максимальное количество учеников.");
            else throw new IllegalArgumentException("Не указан ученик для добавления в список учеников.");
        }
    }

    public void classTime() {
        director.startLearning();
        for (Teacher teacher : teachers) {
            if (teacher != null) {
                for (Pupil pupil : pupils) {
                    if (teacher.getSubject().equalsIgnoreCase(pupil.getSubject())) {
                        pupil.study();
                        /*System.out.println("Учитель " + teacher.name +
                                " обучил ученика " + pupil.name +
                                " предмету " + teacher.getSubject() +
                                ". Уровень знания ученика увеличился до " + pupil.getKnowledge());*/
                    }
                }
            }
        }
        director.finishLearning();
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", director=" + director +
                ", teachers=" + Arrays.toString(teachers) +
                ", pupils=" + Arrays.toString(pupils) +
                '}';
    }
}
