package com.example.lab7;

public class Subject {
    Class cl;
    Double points;
    String group_name;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Subject(Class cl, Double points) {
        this.cl = cl;
        this.points = points;
        this.group_name = cl.getGroup_name();
    }

    public Class getCl() {
        return cl;
    }

    public Double getPoints() {
        return points;
    }
}
