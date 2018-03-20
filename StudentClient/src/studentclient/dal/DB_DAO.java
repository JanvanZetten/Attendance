
package studentclient.dal;

import sharedclasses.dal.DALException;
import sharedclasses.dal.DBConnecter;
import sharedclasses.dal.Shared_DB_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sharedclasses.be.Student;


/**
 *
 * @author Alex
 */
public class DB_DAO 
{

    DBConnecter connecter;
    Shared_DB_DAO shared;

    public DB_DAO()
    {
        connecter = new DBConnecter();
        shared = new Shared_DB_DAO(false);
    }
    
    public void SOMEMETHOD() throws DALException
    {
        try (Connection con = connecter.getConnection())
        {
            String sql = "SELECT * FROM Message";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    
    /**
     * Get the student with this login
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if there was an error or when the username password combo does not exist in database
     */
    public Student login(String username, String encryptedPassword) throws DALException{
        try (Connection con = connecter.getConnection())
        {
            
            String sql = "SELECT * FROM Student WHERE username = ? AND password = ?;";
            
            ResultSet rs = shared.CommonLogin(con, sql, username, encryptedPassword);
            
            if (rs.next()){
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                
                Student student = new Student(1, fName + " " + lName, username, encryptedPassword);
                return student;
            }
            else{
                throw new DALException("Check your Username and password");
            }

        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    
    /**
     * set Prensence 
     * @param currentStudent 
     */
    public void setPresence(Student currentStudent){
        
    }
}
