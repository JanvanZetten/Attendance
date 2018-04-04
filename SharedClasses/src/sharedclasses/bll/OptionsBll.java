/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

import java.util.logging.Level;
import java.util.logging.Logger;
import sharedclasses.be.UserOptions;
import sharedclasses.dal.DALException;
import sharedclasses.dal.OptionsData;

/**
 *
 * @author janvanzetten
 */
public class OptionsBll {
    
    private OptionsData dal;

    public OptionsBll() {
        this.dal = new OptionsData();
    }
    
    public void saveOptions(UserOptions options) throws BLLException{
        try {
            dal.saveOptions(options);
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }
    
    public UserOptions loadOptiones() throws BLLException{
        try {
            return dal.loadOptions();
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }
    
}
