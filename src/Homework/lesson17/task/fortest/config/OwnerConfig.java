package Homework.lesson17.task.fortest.config;

import Homework.lesson17.task.dicontainer.marks.ConfigClass;

@ConfigClass(file = "app.properties", prefix = "owner")
public class OwnerConfig {
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
