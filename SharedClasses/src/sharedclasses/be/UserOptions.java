/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

import org.jasypt.util.text.StrongTextEncryptor;

/**
 *
 * @author janvanzetten
 */
public class UserOptions
{

    static private boolean rememberMe = false;
    static private String user;
    static private String pass;
    StrongTextEncryptor textEncryptor;

    public UserOptions()
    {
        //password encryption setup:
        textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword("anfopnu 83945gpÄNSU JVEWNIUVOÅAs dvuis nIEOV678NÅ AVASND* DSJV y768¨´´#dmsal ævpewui");
    }

    public String getUsername()
    {
        return user;
    }

    public String getPassword()
    {
        return textEncryptor.decrypt(pass);
    }

    public boolean getRememberMe()
    {
        return rememberMe;
    }

    public void rememberMe(String UserName, String Password)
    {
        rememberMe = true;
        user = UserName;
        String encryptedString = textEncryptor.encrypt(Password);
        pass = encryptedString;
    }

    public void dontRememberMe()
    {
        rememberMe = false;
        user = null;
        pass = null;
    }
}
