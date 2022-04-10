package com.example.lab7;

import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialversionUID = 3L;
    private static int ids=0;

    @CsvExport(nr = 0)
    int id;

    @CsvExport(nr = 3)
    int value;

    @CsvExport(nr = 1)
    int id_subject;

    @CsvExport(nr = 2)
    int id_student;

    public Grade(int value, int id_subject, int id_student) {
        this.value = value;
        this.id_subject = id_subject;
        this.id_student = id_student;
        ids++;
        id=ids;
    }

    public Grade(){}

    public static void setIds(int id)
    {
        ids = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", value=" + value +
                ", id_subject=" + id_subject +
                ", id_student=" + id_student +
                '}';
    }
}
