    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.data;
import teacherclient.be.SchoolClass;


/**
 *
 * @author alexl
 */
public class CurrentData {
    
    private SchoolClass currentClass;
    
    /**
     * Sets the currently selected class.
     * @param sentClass 
     */
    public void setCurrentClass(SchoolClass sentClass) {
        currentClass = sentClass;
    }

    /**
     * Gets the currently selected class.
     * @return 
     */
    public SchoolClass getCurrentClass() {
        return currentClass;
    }
    
    
    
}
