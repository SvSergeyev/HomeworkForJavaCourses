package Homework.Lesson6.GroupFormation;

import java.util.Arrays;

public class Group {
    // Группа для восхождения на гору создаётся со следующими характеристиками:
    // - идёт набор в группу или нет;
    // - массив альпинистов;
    // - гора;
    // - должна быть возможность добавить альпиниста в группу,
    // если набор ещё идёт.

    private boolean isOpen;     // идет ли набор в группу
//    private int groupSize;      // размер массива альпинистов
    private Climber[] climbers; // массив альпинистов
    private Mountain mountain;  // гора
    private int count;

//    public boolean isOpen() {
//        return isOpen;
//    }
    public void setOpen() {
        isOpen = true;
    }
    public void setClose() {
        isOpen = false;
    }

    public void setMountain(Mountain mountain) {
        if (mountain == null) throw new IllegalArgumentException("Не задана гора для восхождения.");
        this.mountain = mountain;
    }

    public Group(int groupSize, Mountain mountain) {
        if (groupSize < 1) throw new IllegalArgumentException("Размер группы не может быть меньше 1.");
        climbers = new Climber[groupSize];
        setMountain(mountain);
        setOpen();
    }

    public void addClimber(Climber climber) {
        if (isOpen && climber != null) {
            climbers[count] = climber;
            count++;
            if (count == climbers.length) setClose();
        } else if (!isOpen) System.out.println("Набор в группу закрыт, последний альпинист не был добавлен.");
    }

    public void addClimber(Climber... climbers) {
        for (Climber climber : climbers) {
           if (isOpen && climber != null) {
               this.climbers[count++] = climber;
               if (count == this.climbers.length) setClose();
           }
           else if (!isOpen) System.out.println("Набор в группу закрыт, некоторые альпинисты не были добавлены.");
           else throw new IllegalArgumentException("В списке отсутствует альпинист.");
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "isOpen=" + isOpen +
                ", climbers=" + Arrays.toString(climbers) +
                ", mountain=" + mountain +
                '}';
    }
}
