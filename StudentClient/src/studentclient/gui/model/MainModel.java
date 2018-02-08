/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
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
        PopUpBubble pub = new PopUpBubble(button, "Presence registred!", new Color(84.0 / 255.0, 173.0 / 255.0, 50.0 / 255.0, 255.0 / 255.0));
    }

    public void showAbsenceGraph()
    {
        try {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/studentclient/gui/view/AbsenceWindow.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            newStage.setTitle("Absence");
            newStage.setScene(scene);
            newStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
