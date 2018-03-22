/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.dal;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
import sharedclasses.dal.DALException;
import sharedclasses.dal.Shared_DB_DAO;

/**
 *
 * @author janvanzetten
 */
public class DalManager implements DALFacade {
    
    DB_DAO studentDB;
    Shared_DB_DAO sharedDB;
    MockDAO mock;

    public DalManager() {
        studentDB = new DB_DAO();
        sharedDB = new Shared_DB_DAO(false);
        
        mock = new MockDAO();
    }

    @Override
    public List<ScheduleItem> getSchedueleItems() {
        return mock.getSchedueleItems();
    }

    @Override
    public XYChart.Series<String, Number> getChartSeries() {
        return mock.getChartSeries();
    }

    @Override
    public List<Student> getStudents() {
        return mock.getStudents();
    }

    @Override
    public Student login(String username, String encryptedPassword) throws DALException {
        return studentDB.login(username, encryptedPassword);
    }

    @Override
    public void setPresence(Student activeUser) throws DALException {
        studentDB.setPresence(activeUser);
    }

    @Override
    public boolean getPresence(Student activeUser) throws DALException{
            return studentDB.checkIfPresent(activeUser);
    }
    
}
