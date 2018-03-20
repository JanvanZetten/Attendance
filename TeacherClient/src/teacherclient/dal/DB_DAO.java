/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import sharedclasses.be.Teacher;
import sharedclasses.dal.DALException;
import sharedclasses.dal.DBConnecter;
import sharedclasses.dal.Shared_DB_DAO;

/**
 *
 * @author janvanzetten
 */
public class DB_DAO {
    DBConnecter connecter;
    Shared_DB_DAO shared;

    public DB_DAO() {
        connecter = new DBConnecter();
        shared = new Shared_DB_DAO(false);
    }
    
    
    /**
     * Get the teacher with this login
     *
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if there was an error or when the username password
     * combo does not exist in database
     */
    public Teacher login(String username, String encryptedPassword) throws DALException {
        try (Connection con = connecter.getConnection()) {

            String sql = "SELECT * FROM Teacher WHERE username = ? AND password = ?;";

            ResultSet rs = shared.CommonLogin(con, sql, username, encryptedPassword);

            if (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");

                Teacher teacher = new Teacher(1, fName + " " + lName, username, encryptedPassword);
                return teacher;
            } else {
                throw new DALException("Check your Username and password");
            }

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
}
