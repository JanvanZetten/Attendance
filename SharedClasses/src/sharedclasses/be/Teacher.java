/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class Teacher
{
    private int id;
    private String name;
    private String username;
    private List<SchoolClass> classes;

    /**
     * Teacher
     * @param id teacher id from database.
     * @param name teacher name.
     * @param username teacher username.
     */
    public Teacher(int id, String name, String username)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.classes = new ArrayList<>();
    }

    /**
     * Get id.
     * @return id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Get name.
     * @return name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get username.
     * @return username.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Compare on id.
     * @param obj
     * @return
     */
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
        final Teacher other = (Teacher) obj;
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
        this.classes.clear();
        this.classes.addAll(classes);
    }

}
