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
public class ClassRoom
{
    private String name;

    /**
     * Constructor for Class Rooms.
     * @param name 
     */
    public ClassRoom(String name)
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
