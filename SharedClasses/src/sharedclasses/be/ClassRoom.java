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
public class ClassRoom
{
    private String name;

    /**
     * ClassRoom with given name.
     * @param name Name of classroom.
     */
    public ClassRoom(String name)
    {
        this.name = name;
    }

    /**
     * Gets name of ClassRoom.
     * @return the name of the ClassRoom.
     */
    public String getName()
    {
        return name;
    }
}
