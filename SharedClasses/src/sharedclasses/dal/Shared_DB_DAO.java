/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author janvanzetten
 */
public class Shared_DB_DAO {

    DBConnecter connecter;
    String role;

    public Shared_DB_DAO(boolean isTeacher) {
        connecter = new DBConnecter();

        if (isTeacher) {
            role = "Teacher";
        } else {
            role = "Student";
        }
    }
    
    
    /**
     * returns a result set based on login information
     * @param con the connection on which to sent the sql
     * @param sql the sql for an prepared statement with ? where the username and password have to be in that order
     * @param username the username to for the login
     * @param encryptedPassword the password for the login
     * @return resultset with the return information
     * @throws SQLException 
     */
    public ResultSet CommonLogin(Connection con, String sql, String username, String encryptedPassword) throws SQLException{
            
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, username);
            statement.setString(2, encryptedPassword);
           
            return statement.executeQuery();
         
    }
             

}
