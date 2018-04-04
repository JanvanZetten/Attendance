/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Asbamz
 */
public class UserPropertiesDAO
{
    private final static String PROP_DIR = "auto.properties";
    private final static String PROP_USERNAME = "propusrnme";
    private final static String PROP_PASSWORD = "proppswd";

    private static Properties prop = new Properties();

    public static void loadAutoLogin() throws IOException
    {
        prop.load(new FileInputStream(PROP_DIR));
    }

    public static void saveAutoLogin() throws IOException
    {
        prop.store(new FileOutputStream(PROP_DIR), null);
    }

    public static String getUsername()
    {
        return prop.getProperty(PROP_USERNAME);
    }

    public static String getPassword()
    {
        return prop.getProperty(PROP_PASSWORD);
    }

    public static void setUsername(String username)
    {
        prop.setProperty(PROP_USERNAME, username);
    }

    public static void setPassword(String password)
    {
        prop.setProperty(PROP_PASSWORD, password);
    }

    public static void clearProperties() throws IOException
    {
        prop.clear();
        saveAutoLogin();
    }
}
