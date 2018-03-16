
package studentclient.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import studentclient.be.Student;

/**
 *
 * @author Alex
 */
public class DB_DAO 
{

    DBConnecter connecter;

    public DB_DAO()
    {
        connecter = new DBConnecter();
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
     * Get student the student with this login
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if there was an error or when the username password combo does not exist in database
     */
    public Student login(String username, String encryptedPassword) throws DALException{
        try (Connection con = connecter.getConnection())
        {
            
            String sql = "SELECT * FROM Student WHERE username = ? AND password = ?;";
            
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, username);
            statement.setString(2, encryptedPassword);
            
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            
            if (rs.next()){
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                
                Student student = new Student(id, fName + lName, username, encryptedPassword);
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
}
