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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import studentclient.be.ScheduleItem;
import studentclient.be.Student;
import studentclient.bll.BllManager;
import studentclient.gui.controller.AbsenceWindowController;

/**
 *
 * @author janvanzetten
 */
public class MainModel
{
    private Schedule schedule;
    private BllManager bm;
    private Student activeUser;

    public MainModel()
    {
        schedule = new Schedule(8, 16);
        bm = new BllManager();
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
        schedule.setupCourses(courses, activeUser);
    }

    public void handlePresent(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        PopUpBubble pub = new PopUpBubble(button, "Presence registred!", Color.web("#54AD32"));
        
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(2500),
            ae -> button.setDisable(false)));
        timeline.play();
        
    }

    public void showAbsenceGraph()
    {
        try
        {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/studentclient/gui/view/AbsenceWindow.fxml"));
            Parent root = fxLoader.load();

            AbsenceWindowController awc = (AbsenceWindowController) fxLoader.getController();
            awc.passData(bm.getChartSeries());

            Scene scene = new Scene(root);
            newStage.setTitle("Absence");
            newStage.setScene(scene);
            newStage.showAndWait();
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Student activeUser)
    {
        this.activeUser = activeUser;
    }
}
