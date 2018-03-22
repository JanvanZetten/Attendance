/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import java.util.List;
import sharedclasses.be.SchoolClass;
import sharedclasses.be.Student;
import sharedclasses.be.Teacher;
import sharedclasses.dal.DALException;

/**
 *
 * @author janvanzetten
 */
public class DalManager
{
    DB_DAO db;

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
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws DALException
    {
        return db.getStudentsInClass(schoolClass);
    }

}
