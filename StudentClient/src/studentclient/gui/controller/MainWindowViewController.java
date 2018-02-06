/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import studentclient.be.Course;
import studentclient.be.ScheduleDay;
import studentclient.be.SchoolClass;
import studentclient.gui.model.MainModel;
import studentclient.gui.model.Schedule;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainWindowViewController implements Initializable
{
    @FXML
    private Label lblWeek;
    @FXML
    private AnchorPane scheduleAnchor;

    private MainModel mainModel;
    
    int weekNumber = 10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        mainModel = new MainModel();
        scheduleAnchor.getChildren().add(mainModel.getSchedule());
        mainModel.getSchedule().prefWidthProperty().bind(scheduleAnchor.widthProperty());
        mainModel.getSchedule().prefHeightProperty().bind(scheduleAnchor.heightProperty());
    }

    /**
     * Setup schedule.
     * @param courses in schedule.
     */
    public void updateSchedule(List<Course> courses)
    {
        mainModel.updateSchedule(courses);
    }

    @FXML
    private void handleAbsenceGraph(ActionEvent event)
    {
        mainModel.showAbsenceGraph();
    }

    @FXML
    private void handlePresent(ActionEvent event)
    {
        mainModel.handlePresent(event);
        
    }

    @FXML
    private void handleNextWeek(MouseEvent event)
    {
        if (weekNumber < 52){
            weekNumber ++;
        }
        else{
             weekNumber = 1;
        }
        
        lblWeek.setText("Week " + weekNumber);
    }

    @FXML
    private void handlePreviousWeek(MouseEvent event)
    {
        if (weekNumber > 1){
            weekNumber --;
        }
        else{
             weekNumber = 52;
        }
        
        lblWeek.setText("Week " + weekNumber);
    }

}
