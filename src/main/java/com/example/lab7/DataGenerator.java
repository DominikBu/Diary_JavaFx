package com.example.lab7;
public class DataGenerator {
    ClassContainer group;
    DataGenerator()
    {
        Student s = new Student("Dominik", "Szkolny", 1999);
        Student a = new Student("Jan", "Kowalski", 2000);
        Student b = new Student("Dominik", "Noak", 1988);
        Student c = new Student("Marta", "Tara", 1999);
        Student d = new Student("Marysia", "Hgo", 2005);
        Student e = new Student("Amelia", "Lego", 1999);

        Class cl = new Class("Matematyka", 6);

        cl.addStudent(a);
        cl.addStudent(b);
        cl.addStudent(c);
        cl.addStudent(d);
        cl.addStudent(s);
        cl.addStudent(e);

        a.addGrade(cl.getId(), 3);
        a.addGrade(cl.getId(), 4);

        a.setStudent_con(StudentCondition.CHORY, cl.getId());

        s = new Student("Dominik", "Szkolny", 1999);
        a = new Student("Jan", "Kowalski", 2000);
        b = new Student("Dominik", "Nowak", 1978);
        c = new Student("Marta", "Tra", 1999);
        d = new Student("Marysia", "Hugo", 2000);

        Class cl1 = new Class("Język angielski", 7);

        cl1.addStudent(a);
        cl1.addStudent(b);
        cl1.addStudent(c);
        cl1.addStudent(d);
        cl1.addStudent(s);
        cl1.addStudent(e);

        s = new Student("Hugo", "Slny", 1999);
        a = new Student("Jan", "Kowalki", 2000);
        b = new Student("Dominik", "Nowak", 1998);
        c = new Student("Marta", "Tara", 1999);
        d = new Student("Marysia", "Hgo", 2000);
        e = new Student("Amelia", "Lego", 1999);

        Class cl2 = new Class("Fizyka", 10);

        cl2.addStudent(a);
        cl2.addStudent(b);
        cl2.addStudent(c);
        cl2.addStudent(d);
        cl2.addStudent(s);
        cl2.addStudent(e);

        group = new ClassContainer();

        group.setClass(cl);
        group.setClass(cl1);
        group.setClass(cl2);
    }

    public ClassContainer getGroup() {
        return group;
    }
}
