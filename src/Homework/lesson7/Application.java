package Homework.lesson7;

public class Application {
    public static void main(String[] args) {
        Director director = new Director("Ivan Ivanovich Directorov", 50);

        School school = new School("№11", 2, 5, director);

        Teacher teacher1 = new Teacher("Pyotr Istoriev", 35, "History");
        Teacher teacher2 = new Teacher("Samson Geometryev", 40, "Geometry");
        school.addTeacher(teacher1, teacher2);

        Pupil pupil1 = new Pupil("Magomet", 12, "History", 0);
        Pupil pupil2 = new Pupil("Alexey", 14, "History", 5);
        Pupil pupil3 = new Pupil("Vasily", 10, "Geometry", 2);
        Pupil pupil4 = new Pupil("Oleg", 7, "Geometry", 11);
        Pupil pupil5 = new Pupil("Igor", 15, "Painting", 3);
        school.addPupil(pupil1, pupil2, pupil3, pupil4, pupil5);

        school.classTime();
    }
}
