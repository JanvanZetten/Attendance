/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

/**
 *
 * @author Asbamz
 */
public class Course
{
    private String name;

    /**
     * A Course.
     * @param name the name of the course.
     */
    public Course(String name)
    {
        this.name = name;
    }

    /**
     * The name of the Course.
     * @return the name of the Course.
     */
    public String getName()
    {
        return name;
    }
}
