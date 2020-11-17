package Homework.Lesson17;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//        ReflectiveClass testObject = new ReflectiveClass(
//                "name",
//                10);
//        ReflectiveClass.toString(testObject);
//        ReflectiveClass.toString("string for example");
//        ReflectiveClass.toString(123);
//        ReflectiveClass.toString(new int[]{1, 2, 3, 4, 5});

        ReflectiveClass testObject = new ReflectiveClass("name", 10);
        Class<? extends ReflectiveClass> testObjectClass = testObject.getClass();
        Annotation[] annotations = testObjectClass.getAnnotations();
        System.out.println("Аннотации класса " + testObjectClass.getName() + ": " + Arrays.toString(annotations));

        Field[] fields = testObjectClass.getFields();
        HashSet<Field> fieldHashSet = new HashSet<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Required.class)) {
                fieldHashSet.add(field);
            }
        }
        System.out.println(fieldHashSet);




    }
}
