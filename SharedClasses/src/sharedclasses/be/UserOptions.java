/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

/**
 *
 * @author janvanzetten
 */
public class UserOptions
{
    private static boolean rememberMe;
    private static String user;
    private static String pass;

    public UserOptions()
    {
    }

    public UserOptions(String user, String password, boolean remember)
    {
        this.user = user;
        this.pass = password;
        this.rememberMe = remember;
    }

    public String getUsername()
    {
        return user;
    }

    public String getPassword()
    {
        return pass;
    }

    public boolean getRememberMe()
    {
        return rememberMe;
    }

    public void dontRememberMe()
    {
        rememberMe = false;
        user = null;
        pass = null;
    }
}
