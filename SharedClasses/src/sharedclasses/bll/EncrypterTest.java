/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asbamz
 */
public class EncrypterTest
{
    /**
     * Test to see if the result is always the same.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String str = "TEST";
        try
        {
            for (int i = 0; i < 10; i++)
            {
                String encrypted = Encrypter.encrypt(str);
                System.out.println(encrypted);
            }
        }
        catch (BLLException ex)
        {
            Logger.getLogger(EncrypterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
