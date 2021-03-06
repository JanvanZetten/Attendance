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
             String sql = "SELECT cl.* FROM Class cl JOIN TeacherOfClass tc ON cl.id = tc.classId WHERE tc.teacherId = ?";
             
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
     * Get a list of all the dates in a class
     * @param schoolClass
     * @return list of dates
     * @throws DALException 
     */
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws DALException{
        try (Connection con = connecter.getConnection()) {
             String sql = "SELECT st.* FROM Student st JOIN StudentsInClass sc ON st.id = sc.studentId WHERE  sc.classId = ?";
             
              PreparedStatement statement = con.prepareStatement(sql);
            
              statement.setInt(1, schoolClass.getId());
              
              ResultSet rs = statement.executeQuery();
              
              List<Student> students = new ArrayList<>();
              
              while (rs.next()){
                  int id = rs.getInt("id");
                  String name = rs.getString("fName") + " " + rs.getString("lName");
                  String username = rs.getString("username");
                  Student student = new Student(id, name, username);
                  students.add(student);
              }
              
              return students;
            
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * Get all the days where the Student has pressed present
     * @param student the date to check for
     * @return a list og date objects where the date was present
     * @throws DALException 
     */
    public List<Date> getPresentDays(Student student) throws DALException{
        try (Connection con = connecter.getConnection()) {
             String sql = "SELECT date FROM StudentPresent WHERE studentID = ?";
             
              PreparedStatement statement = con.prepareStatement(sql);
            
              statement.setInt(1, student.getId());
              
              ResultSet rs = statement.executeQuery();
              
              List<Date> dates = new ArrayList<>();
              
              while (rs.next()){
                  dates.add(rs.getDate("date"));
              }
              
              return dates;
            
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
}
