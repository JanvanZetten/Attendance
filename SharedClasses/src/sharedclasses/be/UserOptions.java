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

    /**
     * User Options.
     */
    public UserOptions()
    {
    }

    /**
     * User Options with information.
     * @param user username.
     * @param password password.
     * @param remember remember.
     */
    public UserOptions(String user, String password, boolean remember)
    {
        UserOptions.user = user;
        UserOptions.pass = password;
        UserOptions.rememberMe = remember;
    }

    /**
     * Get username.
     * @return username.
     */
    public String getUsername()
    {
        return UserOptions.user;
    }

    /**
     * Get password.
     * @return password.
     */
    public String getPassword()
    {
        return UserOptions.pass;
    }

    /**
     * Get remember me.
     * @return remember me.
     */
    public boolean getRememberMe()
    {
        return UserOptions.rememberMe;
    }

    /**
     * Reset object.
     */
    public void dontRememberMe()
    {
        UserOptions.rememberMe = false;
        UserOptions.user = null;
        UserOptions.pass = null;
    }
}
