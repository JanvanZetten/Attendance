/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import java.time.LocalDate;
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
public interface DalFacade
{

    /**
     * login the Teacher if it exists return object
     * @param username
     * @param encryptedPassword
     * @return
     * @throws DALException if something went wrong like that the teacher is not
     * in the database
     */
    public Teacher login(String username, String encryptedPassword) throws DALException;

    /**
     * Get a list of all the students in a class
     * @param schoolClass
     * @return list of students
     * @throws DALException
     */
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws DALException;

    /**
     * Get all the days where the Student has pressed present
     * @param student the date to check for
     * @return a list og date objects where the date was present
     * @throws DALException
     */
    public List<Date> getPresentDays(Student student) throws DALException;

    /**
     * Get the latest saved interval startdate or if there is not any gets the 1. of august of this or the last year
     * @return LocalDate object
     */
    public LocalDate getIntevalStartDate();
}
