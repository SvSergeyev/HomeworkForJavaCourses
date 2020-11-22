package Homework.lesson22.Tasks3_4_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class University {
    private List<Course> courses = new ArrayList<>();

    public boolean addCourse(Course course) {
        return courses.add(Objects.requireNonNull(course));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> filtered(Predicate<Course> filter){
        List<Course> filteredCourses = new ArrayList<>();
        // TODO:: если курс подходит по filter, добавить его в filteredCourses
        return filteredCourses;
    }

}
