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

/**
 *
 * @author janvanzetten
 */
public interface DALFacade {

    public List<ScheduleItem> getSchedueleItems();

    public XYChart.Series<String, Number> getChartSeries();

    public List<Student> getStudents();

    public Student login(String username, String encryptedPassword);
    
}
