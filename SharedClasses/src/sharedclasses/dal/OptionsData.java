/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.dal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sharedclasses.be.UserOptions;

/**
 *
 * @author janvanzetten
 */
public class OptionsData {
    
    private final String OPTIONS_FILE = "options.easv";
    private static UserOptions options;
    
     /**
     * Saves the given options to a file
     *
     * @param options
     * @throws DALException
     */
    public void saveOptions(UserOptions options) throws DALException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OPTIONS_FILE)))
        {
            oos.writeObject(options);
            OptionsData.options = options;
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Loads the options from the file.
     *
     * @return the settings from the file
     * @throws DALException
     */
    public UserOptions loadOptions() throws DALException
    {
        if (options == null)
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OPTIONS_FILE)))
            {
                options = (UserOptions) ois.readObject();
            }
            catch (FileNotFoundException ex)
            {
                saveOptions(new UserOptions());
            }
            catch (IOException | ClassNotFoundException ex)
            {
                throw new DALException(ex.getMessage(), ex.getCause());
            }
        }
        return options;
    }
    
}
