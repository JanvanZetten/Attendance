/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.bll;

import java.util.List;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
import studentclient.dal.DALFacade;
import studentclient.dal.DalManager;
import studentclient.dal.MockDAO;

/**
 *
 * @author janvanzetten
 */
public class BllManager
{
    private DALFacade dal = new DalManager();

    public List<ScheduleItem> getScheduleItems()
    {
        return dal.getSchedueleItems();
    }

    public XYChart.Series<String, Number> getChartSeries()
    {
        return dal.getChartSeries();
    }

    public List<Student> getStudents()
    {
        return dal.getStudents();
    }

    public void setPresent(boolean b) {
        
    }
    
    public Student login(String username, String encryptedPassword){
        
        return dal.login(username, encryptedPassword);
    }

}
