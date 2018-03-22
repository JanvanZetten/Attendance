package studentclient.dal;

import sharedclasses.dal.DALException;
import sharedclasses.dal.DBConnecter;
import sharedclasses.dal.Shared_DB_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sharedclasses.be.Student;

/**
 *
 * @author Alex
 */
public class DB_DAO {

    DBConnecter connecter;
    Shared_DB_DAO shared;

    public DB_DAO() {
        connecter = new DBConnecter();
        shared = new Shared_DB_DAO(false);
    }

    /**
     * Get the student with this login
     *
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if there was an error or when the username password
     * combo does not exist in database
     */
    public Student login(String username, String encryptedPassword) throws DALException {
        try (Connection con = connecter.getConnection()) {

            String sql = "SELECT * FROM Student WHERE username = ? AND password = ?;";

            ResultSet rs = shared.CommonLogin(con, sql, username, encryptedPassword);

            if (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");

                Student student = new Student(id, fName + " " + lName, username);
                return student;
            } else {
                throw new DALException("Check your Username and password");
            }

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * set Prensence for the currentStudent on the currentTime
     *
     * @param currentStudent
     */
    public void setPresence(Student currentStudent) throws DALException {
        try (Connection con = connecter.getConnection()) {
            
            String sql = "INSERT INTO StudentPresent VALUES (?, ?)";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, currentStudent.getId());
            Date date = new Date();
            Object param = new java.sql.Timestamp(date.getTime());
            statement.setObject(2, param);
            
            statement.execute();

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * Gets the presence of the student upon log-in.
     *
     * @param currentStudent
     */
    public boolean getPresence(Student currentStudent) throws DALException {
        try (Connection con = connecter.getConnection()) {
            
            String sql = "SELECT date FROM StudentPresent WHERE studentId = ?";
            
            System.out.println("1");
            
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            System.out.println(currentStudent.getId());
            
            statement.setInt(1, currentStudent.getId());
            
            Calendar date = Calendar.getInstance();
            statement.setString(2, "%" + date.get(Calendar.YEAR) + "-0" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DATE) + "%");

            ResultSet rs = statement.executeQuery();
            
            System.out.println("2");
            
            boolean absent = false;
            while (rs.next())
            {
                System.out.println("3");
                absent = true;
                System.out.println("4");
            }
            
            System.out.println("5");
            
            return absent;

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }   
}
