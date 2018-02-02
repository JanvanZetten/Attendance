/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.util.List;

/**
 *
 * @author alexl
 */
public class Student {
    
    private int id;
    private String name;
    private List<Class> classes;
    
    public Student (int id, String name, List<Class> classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", classes=" + classes + '}';
    }
    
    
}
