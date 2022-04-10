package com.example.lab7;
import java.io.Serializable;
import java.util.*;

import static java.lang.Math.round;

public class Student implements Comparable<Student>, Serializable {
    private static final long serialversionUID = 4L;
    private static int ids=0;

    @CsvExport(nr = 0)
    int id;

    Map<Integer, Grade[]> grades = new HashMap<>();
    transient Map<Integer, StudentCondition> student_con = new HashMap<>();

    @CsvExport(nr = 2)
    String name;

    @CsvExport(nr = 3)
    String sname;

    @CsvExport(nr = 1)
    int year;

    public static void setIds(int id)
    {
        ids = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIds() {
        return ids;
    }

    public int getId() {
        return id;
    }

    public List<Grade> getGrades(int subject_id ) {

        if(grades.get(subject_id) == null)
            return null;
        List<Grade> grade = new ArrayList<Grade>();

        for(int i=0;i<grades.get(subject_id).length;i++)
        {
            grade.add(grades.get(subject_id)[i]);
        }

        return grade;
    }

    public void addGrade(int subject_id, int value)
    {
        if(grades.get(subject_id) == null)
        {
            Grade[] g = new Grade[1];
            g[0] =  new Grade(value, subject_id, id);
            grades.put(subject_id, g);

        }else {
            Grade[] values = new Grade[getGrades(subject_id).size() + 1];
            for (int i = 0; i < values.length - 1; i++)
                values[i] = grades.get(subject_id)[i];
            values[values.length - 1] = new Grade(value, subject_id, id);
            grades.put(subject_id, values);
        }

    }

    public void addGradeAll(int subject_id, int value, int id_grade)
    {
        if(grades.get(subject_id) == null)
        {
            Grade[] g = new Grade[1];
            g[0] =  new Grade(value, subject_id, id);
            g[0].setId(id_grade);
            grades.put(subject_id, g);

        }else {
            Grade[] values = new Grade[getGrades(subject_id).size() + 1];
            for (int i = 0; i < values.length - 1; i++)
                values[i] = grades.get(subject_id)[i];
            values[values.length - 1] = new Grade(value, subject_id, id);
            values[values.length - 1].setId(id_grade);
            grades.put(subject_id, values);
        }

    }

    public void removeGrade(Grade g)
    {
        boolean is = false;
        for( Grade gr : getGrades(g.getId_subject()))
        {
            if(gr.equals(g)) {
                is = true;
                break;
            }
        }
        if(!is)
            return;

        Grade[] grade = new Grade[getGrades(g.getId_subject()).size()-1];

        int j=0;
        for(int i=0;i<getGrades(g.getId_subject()).size()-1;i++)
        {
            if(getGrades(g.getId_subject()).get(i).equals(g))
                j++;
            grade[i] = getGrades(g.getId_subject()).get(i+j);
        }

        grades.put(g.getId_subject(), grade);
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public StudentCondition getStudent_con( int subject_id) {
        if(student_con.get(subject_id)==null)
            return StudentCondition.NIEOBECNY;

        return student_con.get(subject_id);
    }

    public double getPoints(int subject_id) {
        if(grades.get(subject_id) == null)
            return 0.0;
        double points=0;
        int i;
        for(i=0; i<grades.get(subject_id).length; i++){
            points += grades.get(subject_id)[i].getValue();
        }
        if(i==0)
            return 0;
        points = points / (double) i;
        return points;
    }

    public double getAllPoints()
    {
        double points=0;
        double allPoints=0;
        int subjects=0;
        int i;
        for(Grade[] x : grades.values())
        {
            if(x.length == 0)
                continue;

            for(i=0; i<x.length; i++)
            {
                points += x[i].getValue();
            }
            points = points/(double) i;
            allPoints += points;

            points=0;
            subjects++;
        }
        allPoints = allPoints/ (double) subjects;
        return allPoints;
    }

    public void setStudent_con(StudentCondition c, int subject_id) {
        if(student_con==null)
            student_con = new HashMap<>();
        student_con.put(subject_id,c);
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    Student()
    {
    }

    Student(String i, String n, int ru) {
        name = i;
        sname = n;
        year = ru;
        ids++;
        id=ids;
    }

    public void print()
    {
        System.out.println( "Student "+ name + " " + sname + " urodzony " + year + "r. otrzymał " + round(getAllPoints()) + "pkt.");
    }

    @Override
    public int compareTo(Student o) {
        int result = this.getSname().compareTo(o.getSname());
        return result;
    }
}
