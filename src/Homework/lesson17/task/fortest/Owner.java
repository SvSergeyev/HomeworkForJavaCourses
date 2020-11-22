package Homework.lesson17.task.fortest;


import Homework.lesson17.task.dicontainer.marks.RequiredClass;
import Homework.lesson17.task.dicontainer.marks.RequiredField;
import Homework.lesson17.task.fortest.config.OwnerConfig;

@RequiredClass
public class Owner {
    @RequiredField
    private OwnerConfig ownerConfig;
    private String name;

    public Owner() {
        this.name = ownerConfig.getOwnerName();
    }
}
