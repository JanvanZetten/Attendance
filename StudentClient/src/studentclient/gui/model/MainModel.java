/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import studentclient.be.Course;

/**
 *
 * @author janvanzetten
 */
public class MainModel
{

    private Schedule schedule;

    public MainModel()
    {
        schedule = new Schedule(8, 16);
    }

    public Schedule getSchedule()
    {
        return schedule;
    }

    /**
     * Update courses in schedule.
     * @param courses in schedule.
     */
    public void updateSchedule(List<Course> courses)
    {
        schedule.setupCourses(courses);
    }

    public void handlePresent(ActionEvent event)
    {
        //TODO make the program send an present notification
        Button button = (Button) event.getSource();
        button.setDisable(true);
    }

    public void showAbsenceGraph()
    {
        //TODO make the progarm open a window with the students absence in a graph view
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not been absent", ButtonType.OK);
        alert.showAndWait();
    }

}
