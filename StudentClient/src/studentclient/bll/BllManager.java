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
import sharedclasses.bll.BLLException;
import sharedclasses.dal.DALException;
import studentclient.dal.DALFacade;
import studentclient.dal.DalManager;

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

    public void setPresent(boolean b, Student activeUser) throws BLLException {
        if (b){
            setPresence(activeUser);
        }
    }
    
    
    /**
     * if the login information is valid return the student with this login if not send exeption
     * @param username
     * @param encryptedPassword
     * @return 
     */
    public Student login(String username, String encryptedPassword) throws BLLException{
        try {
            return dal.login(username, encryptedPassword);
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(),ex.getCause());
        }
    }
    
    
    /**
     * set currentStudent as Present 
     * @throws BLLException 
     */
    private void setPresence(Student activeUser) throws BLLException{
        try {
            dal.setPresence(activeUser);
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(),ex.getCause());
        }
    }

    /**
     * Gets whether a student is present or not.
     * @param activeUser
     * @return
     * @throws DALException 
     */
    public boolean getPresence(Student activeUser) throws DALException {
        return dal.getPresence(activeUser);
    }

}
