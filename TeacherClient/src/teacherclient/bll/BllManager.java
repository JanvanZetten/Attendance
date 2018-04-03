/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.bll;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.SchoolClass;
import sharedclasses.be.Student;
import sharedclasses.be.Teacher;
import sharedclasses.bll.BLLException;
import sharedclasses.dal.DALException;
import teacherclient.dal.DalFacade;
import teacherclient.dal.DalManager;
import teacherclient.dal.MockDAO;

/**
 *
 * @author Asbamz
 */
public class BllManager
{
    private MockDAO mock = new MockDAO();
    private DalFacade dal = new DalManager();

    /**
     * Gets all classes that exist.
     * @return
     */
    public List<SchoolClass> getListAllClasses()
    {
        return mock.getListAllClasses();
    }

    /**
     * Gets all students that exist.
     * @return
     */
    public List<Student> getListAllStudents()
    {
        return mock.getListAllStudents();
    }

    public List<ScheduleItem> getScheduleItems()
    {
        return mock.getSchedueleItems();
    }

    public List<ScheduleItem> getSchedueleItemsTeacher()
    {
        return mock.getSchedueleItemsTeacher();
    }

    public XYChart.Series<String, Number> getChartSeries()
    {
        return mock.getChartSeries();
    }

    public Teacher login(String text, String encryptedPassword) throws BLLException
    {
        try
        {
            return dal.login(text, encryptedPassword);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Get a list of all the students in a class
     * @param schoolClass
     * @return list of students
     * @throws DALException
     */
    public List<Student> getStudentsInClass(SchoolClass schoolClass) throws BLLException
    {
        try
        {
            return dal.getStudentsInClass(schoolClass);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Get all the days where the Student has pressed present
     * @param student the date to check for
     * @return a list og date objects where the date was present
     * @throws BLLException
     */
    public List<Date> getPresentDays(Student student) throws BLLException
    {
        try
        {
            return dal.getPresentDays(student);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Get the latest saved interval startdate or if there is not any gets the 1. of august of this or the last year
     * @return LocalDate object
     */
    public LocalDate getIntevalStartDate() {
        return dal.getIntevalStartDate();
    }
}
