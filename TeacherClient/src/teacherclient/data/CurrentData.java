    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.data;
import teacherclient.be.Class;


/**
 *
 * @author alexl
 */
public class CurrentData {
    
    private Class currentClass;

    public void setCurrentClass(Class sentClass) {
        currentClass = sentClass;
    }

    public Class getCurrentClass() {
        return currentClass;
    }
    
    
    
}
