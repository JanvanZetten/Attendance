/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.bll;

import java.util.List;
import javafx.scene.chart.XYChart;
import teacherclient.be.ScheduleItem;
import teacherclient.be.SchoolClass;
import teacherclient.be.Student;
import teacherclient.dal.MockDAO;

/**
 *
 * @author Asbamz
 */
public class BllManager
{
    private MockDAO mock = new MockDAO();

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
}
