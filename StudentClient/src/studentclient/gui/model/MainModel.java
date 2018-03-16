/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import sharedclasses.gui.model.Schedule;
import sharedclasses.gui.model.PopUpBubble;
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
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
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

    /**
     * Handles the "Present" button actions and opens a popup to notify the
     * user.
     * @param event clicking the button.
     */
    public void handlePresent(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        button.setDisable(true);

        if (button.getText().equals("Present"))
        {
            PopUpBubble pub = new PopUpBubble(button, "Presence registred!", Color.web("#54AD32"));
<<<<<<< HEAD

=======
            bm.setPresent(true);
        
>>>>>>> 007ce3383779d7c15f58c310b4a25cd3d251a8b1
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(2500),
                    action -> changeButton(button, true)));

            timeline.play();
        }
        else if (button.getText().equals("Remove Prescence"))
        {
            PopUpBubble pub = new PopUpBubble(button, "Presence removed!", Color.web("#DB3b26"));
<<<<<<< HEAD

=======
            bm.setPresent(false);
        
>>>>>>> 007ce3383779d7c15f58c310b4a25cd3d251a8b1
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(2500),
                    action -> changeButton(button, false)));

            timeline.play();
        }
    }

    /**
     * Changes the button to Present or Remove Presence when clicking it after a
     * delay.
     * @param button the Present button.
     * @param trueOrFalse True = Pressed Present, False = Pressed Remove
     * Presence.
     */
    private void changeButton(Button button, boolean trueOrFalse)
    {
        button.setDisable(false);

        if (trueOrFalse == true)
        {
            button.setStyle("-fx-background-color: #DB3b26");
            button.setText("Remove Prescence");
        }
        else if (trueOrFalse == false)
        {
            button.setStyle("-fx-background-color: #54AD32");
            button.setText("Present");
        }
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
