/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Asbamz
 */
public class Encrypter
{
    private static MessageDigest digester;

    public static String encrypt(String str) throws BLLException
    {
        if (digester == null)
        {
            synchronized (Encrypter.class)
            {
                try
                {
                    digester = MessageDigest.getInstance("SHA-256");
                }
                catch (NoSuchAlgorithmException ex)
                {
                    throw new BLLException(ex.getMessage(), ex.getCause());
                }
            }
        }

        if (str == null || str.length() == 0)
        {
            throw new BLLException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++)
        {
            if ((0xff & hash[i]) < 0x10)
            {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else
            {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
