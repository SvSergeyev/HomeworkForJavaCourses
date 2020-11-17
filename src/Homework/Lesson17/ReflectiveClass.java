package Homework.Lesson17;

import java.util.Objects;

@Config
public class ReflectiveClass {
    @Required
    private String stringField;
    @Required
    private int intField;

    public ReflectiveClass(String stringField, int intField) {
        setStringField(stringField);
        setIntField(intField);
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }


    public static String toString(Object o) {
        Objects.requireNonNull(o, "Object cannot be null.");

        System.out.println("toString is work.");
        return null; // !!!
    }
}
