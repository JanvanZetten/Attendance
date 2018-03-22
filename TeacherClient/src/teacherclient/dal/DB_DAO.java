/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sharedclasses.be.SchoolClass;
import sharedclasses.be.Student;
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

                Teacher teacher = new Teacher(id, fName + " " + lName, username);
                return teacher;
            } else {
                throw new DALException("Check your Username and password");
            }

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * Get a list of the classes that the given teacher has
     * @param currentTeacher
     * @return a list of classes
     * @throws DALException 
     */
    public List<SchoolClass> getClasses(Teacher currentTeacher)throws DALException{
        try (Connection con = connecter.getConnection()) {
             String sql = "SELECT cl.* FROM Class cl JOIN StudentsInCLass sc ON cl.id = sc.classId WHERE sc.studentId = ?";
             
              PreparedStatement statement = con.prepareStatement(sql);
            
              statement.setInt(1, currentTeacher.getId());
              
              ResultSet rs = statement.executeQuery();
              
              List<SchoolClass> classes = new ArrayList<>();
              
              while (rs.next()){
                  int id = rs.getInt("id");
                  String name = rs.getString("className");
                  SchoolClass schoolClass = new SchoolClass(id, name);
                  classes.add(schoolClass);
              }
              
              return classes;
            
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    
    /**
     * Get a list of all the students in a class
     * @param schoolClass
     * @return list of students
     * @throws DALException 
     */
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws DALException{
        return null;
    }
    
    /**
     * Get all the days where the Student has pressed present
     * @param student the student to check for
     * @return a list og date objects where the student was present
     * @throws DALException 
     */
    public List<Date> getPresentDays(Student student) throws DALException{
        
        
        return null;
        
        
    }
}
