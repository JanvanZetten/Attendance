/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.bll;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.SchoolClass;
import sharedclasses.be.Student;
import sharedclasses.be.Teacher;
import sharedclasses.bll.BLLException;
import sharedclasses.dal.DALException;
import teacherclient.dal.DalManager;
import teacherclient.dal.MockDAO;

/**
 *
 * @author Asbamz
 */
public class BllManager
{
    private MockDAO mock = new MockDAO();
    private DalManager dal = new DalManager();

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

    public Teacher login(String text, String encryptedPassword) throws BLLException {
        try {
            return dal.login(text, encryptedPassword);
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }
    }
}
