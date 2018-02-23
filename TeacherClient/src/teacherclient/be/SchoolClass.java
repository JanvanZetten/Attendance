/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.be;

/**
 *
 * @author alexl
 */
public class SchoolClass {
    
    private int id;
    private String name;
    
    /**
     * Constructor of School Class.
     * @param id
     * @param name 
     */
    public SchoolClass (int id, String name) {
        this.id = id;
        this.name = name;
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
     * To string method of School Class.
     * @return 
     */
    @Override
    public String toString() {
        return "Class{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
