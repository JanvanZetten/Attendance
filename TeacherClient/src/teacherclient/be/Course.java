/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.be;

/**
 *
 * @author Asbamz
 */
public class Course
{
    private String name;

    /**
     * Contructor of Courses.
     * @param name
     */
    public Course(String name)
    {
        this.name = name;
    }

    /**
     * Getter of name.
     * @return 
     */
    public String getName()
    {
        return name;
    }
}
