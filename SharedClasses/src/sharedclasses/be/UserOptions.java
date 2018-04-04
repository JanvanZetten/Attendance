/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

import java.io.Serializable;

/**
 *
 * @author janvanzetten
 */
public class UserOptions implements Serializable
{
    private boolean rememberMe;
    private String user;
    private String pass;

    /**
     * User Options.
     */
    public UserOptions()
    {
        rememberMe = false;
    }

    /**
     * User Options with information.
     * @param user username.
     * @param password password.
     * @param remember remember.
     */
    public UserOptions(String user, String password, boolean remember)
    {
        this.user = user;
        this.pass = password;
        this.rememberMe = remember;
    }

    /**
     * Get username.
     * @return username.
     */
    public String getUsername()
    {
        return user;
    }

    /**
     * Get password.
     * @return password.
     */
    public String getPassword()
    {
        return pass;
    }

    /**
     * Get remember me.
     * @return remember me.
     */
    public boolean getRememberMe()
    {
        return rememberMe;
    }

    /**
     * Reset object.
     */
    public void dontRememberMe()
    {
        rememberMe = false;
        user = null;
        pass = null;
    }
}
