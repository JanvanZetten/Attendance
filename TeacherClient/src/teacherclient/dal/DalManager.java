/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import sharedclasses.be.SchoolClass;
import sharedclasses.be.Student;
import sharedclasses.be.Teacher;
import sharedclasses.dal.DALException;

/**
 *
 * @author janvanzetten
 */
public class DalManager implements DalFacade
{
    DB_DAO db;
    private static LocalDate startdate;
    private static LocalDate enddate;

    public DalManager()
    {
        this.db = new DB_DAO();
    }

    /**
     * login the Teacher if it exists return object
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if something went wrong like that the teacher is not
     * in the database
     */
    @Override
    public Teacher login(String username, String encryptedPassword) throws DALException
    {
        Teacher teacher = db.login(username, encryptedPassword);
        teacher.setClasses(db.getClasses(teacher));
        return teacher;
    }

    /**
     * Get a list of all the students in a class
     * @param schoolClass
     * @return list of students
     * @throws DALException
     */
    @Override
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws DALException
    {
        return db.getStudentsInClass(schoolClass);
    }

    /**
     * Get all the days where the Student has pressed present
     * @param student the date to check for
     * @return a list og date objects where the date was present
     * @throws DALException
     */
    @Override
    public List<Date> getPresentDays(Student student) throws DALException
    {
        return db.getPresentDays(student);
    }

    @Override
    public LocalDate getIntevalStartDate() {
        if (startdate != null){
        } else {
            if (LocalDate.now().getMonth().compareTo(Month.AUGUST) < 0){
                startdate = LocalDate.of(LocalDate.now().getYear()-1, Month.AUGUST, 1);
            }
            else{
                startdate = LocalDate.of(LocalDate.now().getYear(), Month.AUGUST, 1);
            }
        }
        return startdate;
    }

    @Override
    public LocalDate getIntevalEndDate() {
        if (enddate != null){
        } else {
            enddate = LocalDate.now();
        }
        
        return enddate;
    }

    @Override
    public void saveInterval(LocalDate startValue, LocalDate endValue) {
        startdate = startValue;
        enddate = endValue;
    }
}
