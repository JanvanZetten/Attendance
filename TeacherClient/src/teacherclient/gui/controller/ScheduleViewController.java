/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import teacherclient.be.ScheduleItem;
import teacherclient.gui.model.ScheduleModel;

/**
 * FXML Controller class
 *
 * @author Asbamz
 */
public class ScheduleViewController implements Initializable
{
    @FXML
    private AnchorPane schedulePane;

    private ScheduleModel scheduleModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        scheduleModel = new ScheduleModel();
        schedulePane.getChildren().add(scheduleModel.getSchedule());
        scheduleModel.getSchedule().prefWidthProperty().bind(schedulePane.widthProperty());
        scheduleModel.getSchedule().prefHeightProperty().bind(schedulePane.heightProperty());
    }

    /**
     * Setup schedule.
     * @param courses in schedule.
     */
    public void updateSchedule(List<ScheduleItem> courses)
    {
        scheduleModel.updateSchedule(courses);
    }

    /**
     * Planned feature that allows a teacher to create a course in a given time.
     * @param event 
     */
    @FXML
    private void handleScheduleCourse(ActionEvent event)
    {
    }
}
