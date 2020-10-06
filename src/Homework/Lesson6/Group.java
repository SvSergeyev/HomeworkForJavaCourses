package Homework.Lesson6;

import java.util.Arrays;

public class Group {
    // Группа для восхождения на гору создаётся со следующими характеристиками:
    // - идёт набор в группу или нет;
    // - массив альпинистов;
    // - гора;
    // - должна быть возможность добавить альпиниста в группу, если набор ещё идёт.

    // поля класса:
    private boolean recruitment;        // идет ли набор в группу
    private int groupSize;              // размер массива альпинистов
    private Climber[] groupOfClimbers;  // массив альпинистов
    private Mountain mountain;          // гора

    //гет/сет:
    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public boolean isRecruitment() {
        return recruitment;
    }

    public void setRecruitment(boolean recruitment) {
        this.recruitment = recruitment;
    }

    // методы:
    public void addClimber(Climber climber) {
        for (int i = 0; i < groupOfClimbers.length; i++) {
            if (groupOfClimbers[i] == null) {
                groupOfClimbers[i] = climber;
                return;
            }
        }
    }

    // конструкторы:
    public Group() { }

    public Group(int groupSize, Mountain mountain) {
        setGroupSize(groupSize);
        this.mountain = mountain;
    }

    // переопределенные методы:
    @Override
    public String toString() {
        return "Group{" +
                "recruitment=" + recruitment +
                ", groupOfClimbers=" + Arrays.toString(groupOfClimbers) +
                ", mountain=" + mountain +
                '}';
    }
}
