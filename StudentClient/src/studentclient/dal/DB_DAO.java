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
    public boolean checkIfPresent(Student currentStudent) throws DALException {
        try (Connection con = connecter.getConnection()) {

            String sql = "SELECT date FROM StudentPresent WHERE studentId = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, currentStudent.getId());

            Calendar cal1 = Calendar.getInstance();

            ResultSet rs = statement.executeQuery();

            boolean present = false;

            while (rs.next()) {
                if (present == false) {
                    Calendar cal2 = Calendar.getInstance();
                    
                    Date date2 = (rs.getDate("date"));
                    cal2.setTime(date2);
                    
                    boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                            && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
                    
                    if (sameDay == true) {
                        present = true;
                    }
                }
            }

            return present;

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
}
