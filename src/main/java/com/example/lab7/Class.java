package com.example.lab7;
import java.io.Serializable;
import java.util.*;

import static java.lang.Math.abs;

public class Class implements Serializable {
    private static final long serialversionUID = 2L;
    private static int ids=0;

    @CsvExport(nr = 0)
    int id;

    @CsvExport(nr = 1)
    String group_name;

    List<Student> studentlist;

    @CsvExport(nr = 2)
    int max_student;

    public static void setIds(int id)
    {
        ids = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class()
    {}

    public List<Student> getStudentlist() {
        return studentlist;
    }

    public int getId() {
        return id;
    }

    Class(String ng, int ms)
    {
        ids++;
        group_name = ng;
        max_student = ms;
        studentlist = new ArrayList<>(max_student);
        id=ids;
    }

    @Override
    public String toString() {
        return group_name + " " + String.valueOf(max_student);
    }

    public void remove(Student s) throws MyException
    {

        if(!studentlist.remove(s))
            throw new MyException("Nie można usunąć wybranego studenta.");
    }

    public void addStudent(Student other)throws MyExceptionArth
    {
        for(Student s : studentlist){
            if(s.getName().equals(other.getName()))
            {
                //System.out.println("Student o podanym imieniu już istnieje");
                break;
            }
        }

        if(studentlist.size() == max_student)
            throw new MyExceptionArth("Brak miejsca w klasie");

        studentlist.add(other);

        studentlist.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });
    }


    public void getStudent(Student s)
    {
        if(s.getPoints(id) <= 0 )
            studentlist.remove(s);
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public void setMax_student(int max_student) {
        this.max_student = max_student;
    }

    public void changeCondition(Student s, StudentCondition con)
    {
        s.setStudent_con(con, id);
    }

    public Student search(String surname)
    {
        Student s = new Student();
        s.setSname(surname);
        int result = Collections.binarySearch(studentlist, s , new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });

        return studentlist.get(result);
    }

    public List<Student> searchPartial(String str)
    {
        List<Student> l = new LinkedList<>();
        for(Student s : studentlist){
            if(s.getSname().contains(str) || s.getName().contains(str))
            {
                l.add(s);
            }
        }
        return l;
    }
    public void summary()
    {
        System.out.println();
        for(Student s : studentlist){
            s.print();
        }
        System.out.println();
    }

    public List<Student> sortByName()
    {
        List<Student> l = new LinkedList<>();

        for(Student s : studentlist){
            l.add(s);
        }


        Collections.sort(l, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return l;
    }

    public List<Student> sortByPoints()
    {
        List<Student> l = new LinkedList<>();

        for(Student s : studentlist){
            l.add(s);
        }

        Collections.sort(l, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getPoints(id), o2.getPoints(id));
            }
        });

        return l;
    }

    public int getMax_student() {
        return max_student;
    }

    public String getGroup_name() {
        return group_name;
    }

    public Student max()
    {
        Student s1 = Collections.max(studentlist, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getPoints(id), o2.getPoints(id));
            }
        });
        return s1;
    }

}
