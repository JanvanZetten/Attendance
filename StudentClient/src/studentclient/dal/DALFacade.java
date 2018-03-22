/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.dal;

import java.util.List;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
import sharedclasses.dal.DALException;

/**
 *
 * @author janvanzetten
 */
public interface DALFacade {

    public List<ScheduleItem> getSchedueleItems();

    public XYChart.Series<String, Number> getChartSeries();

    public List<Student> getStudents();

    /**
     * if the login information is valid return the student with this login if not send exeption
     * @param username
     * @param encryptedPassword
     * @return 
     */
    public Student login(String username, String encryptedPassword) throws DALException;
    
    public void setPresence(Student activeUser) throws DALException;

    /**
     * Gets the student's presence upon login.
     * @param activeUser 
     */
    public boolean getPresence(Student activeUser) throws DALException;
    
}
