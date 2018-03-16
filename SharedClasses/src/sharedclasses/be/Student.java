/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

import java.util.List;

/**
 *
 * @author Asbamz
 */
public class Student
{
    private int id;
    private String name;
    private String username;
    private String password;
    private List<SchoolClass> classes;

    public Student(int id, String name, String username, String password)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor of Student.
     * @param id
     * @param name
     * @param classes
     */
    public Student(int id, String name, List<SchoolClass> classes)
    {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Student other = (Student) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }

    /**
     * Getter of classes.
     * @return
     */
    public List<SchoolClass> getClasses()
    {
        return classes;
    }

    /**
     * Setter of classes.
     * @param classes
     */
    public void setClasses(List<SchoolClass> classes)
    {
        this.classes = classes;
    }

    @Override
    public String toString()
    {
        return "Student{" + "name=" + name + ", username=" + username + '}';
    }
}
