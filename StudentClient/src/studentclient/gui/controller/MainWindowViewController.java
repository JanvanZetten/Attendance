/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
import studentclient.gui.model.MainModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainWindowViewController implements Initializable
{
    private final int WEEKS_IN_A_YEAR = 52;

    @FXML
    private Label lblWeek;
    @FXML
    private AnchorPane scheduleAnchor;

    private MainModel mainModel;
    private int weekNumber = 10;

    @FXML
    private Button btnPresent;

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

    public void setUser(Student student)
    {
        mainModel.setActiveUser(student);
    }

    /**
     * Setup schedule.
     * @param courses in schedule.
     */
    public void updateSchedule(List<ScheduleItem> courses)
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
        if (weekNumber < WEEKS_IN_A_YEAR)
        {
            weekNumber++;
        }
        else
        {
            weekNumber = 1;
        }

        lblWeek.setText("Week " + weekNumber);
    }

    @FXML
    private void handlePreviousWeek(MouseEvent event)
    {
        if (weekNumber > 1)
        {
            weekNumber--;
        }
        else
        {
            weekNumber = WEEKS_IN_A_YEAR;
        }

        lblWeek.setText("Week " + weekNumber);
    }
}
