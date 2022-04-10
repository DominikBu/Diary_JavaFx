package com.example.lab7;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassContainer implements Serializable {
    private static final long serialversionUID = 1L;
    Map<String, Class> m = new HashMap<>();
    public Map<String, Class> getM() {
        return m;
    }

    public void addClass(String name, double volume)
    {
        Class c = new Class(name, (int) volume);
        m.put(name, c);
    }

    public void setClass(Class c)
    {
        addClass(c.getGroup_name(), c.getMax_student());

        for(Map.Entry<String, Class> e : m.entrySet()) {
            if (e.getValue().group_name.equals(c.group_name)) {
                e.setValue(c);
            }
        }
    }

    public void removeClass(String name)
    {
        m.remove(name);
    }

    public List<Class> findEmpty()
    {
        List<Class> group = new LinkedList<>();
        for(Class klasa : m.values())
        {
            if(klasa.studentlist.size() == 0) {
                group.add(klasa);
            }
        }
        return group;
    }

    public void summary()
    {
        for(Class cl : m.values())
        {
            double p=cl.studentlist.size() * 100.0 / cl.max_student;
            System.out.println( cl.group_name + " " + p + "%");
        }
    }
}
