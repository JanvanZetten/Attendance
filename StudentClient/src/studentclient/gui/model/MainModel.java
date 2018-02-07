/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import studentclient.be.ScheduleItem;

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
    public void updateSchedule(List<ScheduleItem> courses)
    {
        schedule.setupCourses(courses);
    }

    public void handlePresent(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        PopUpBubble pub = new PopUpBubble(button, "Presence registred!");
    }

    public void showAbsenceGraph()
    {
        //TODO make the progarm open a window with the students absence in a graph view
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have not been absent", ButtonType.OK);
        alert.showAndWait();
    }

}
