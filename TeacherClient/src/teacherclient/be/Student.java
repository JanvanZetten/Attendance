/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.be;

import java.util.List;

/**
 *
 * @author alexl
 */
public class Student {
    
    private int id;
    private String name;
    private List<SchoolClass> classes;
    
    /**
     * Constructor of Student.
     * @param id
     * @param name
     * @param classes 
     */
    public Student (int id, String name, List<SchoolClass> classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    /**
     * Getter of ID.
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of ID.
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of name.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of classes.
     * @return 
     */
    public List<SchoolClass> getClasses() {
        return classes;
    }

    /**
     * Setter of classes.
     * @param classes 
     */
    public void setClasses(List<SchoolClass> classes) {
        this.classes = classes;
    }

    /**
     * To String method of Student.
     * @return 
     */
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", classes=" + classes + '}';
    }
    
    
}
