package Homework.lesson17.task.fortest;


import Homework.lesson17.task.dicontainer.marks.RequiredClass;
import Homework.lesson17.task.dicontainer.marks.RequiredField;
import Homework.lesson17.task.fortest.config.MouseConfig;

@RequiredClass
public class Mouse {
    @RequiredField
    private MouseConfig mouseConfig;
    private String name;
    private int speed;

    public Mouse() {
        name = mouseConfig.getMouseName();
        speed = mouseConfig.getMouseSpeed();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
