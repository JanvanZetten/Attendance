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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sharedclasses.be.ScheduleItem;
import sharedclasses.be.Student;
import sharedclasses.bll.BLLException;
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
            try {
                PopUpBubble pub = new PopUpBubble(button, "Presence registred!", Color.web("#54AD32"));
                bm.setPresent(true, activeUser);
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2500),
                        action -> changeButton(button, true)));
                
                timeline.play();
            } catch (BLLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
        else if (button.getText().equals("Remove Prescence"))
        {
            try {
                PopUpBubble pub = new PopUpBubble(button, "Presence removed!", Color.web("#DB3b26"));
                bm.setPresent(false, activeUser);
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2500),
                        action -> changeButton(button, false)));
                
                timeline.play();
            } catch (BLLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
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

    public void logOut(Node anyNode)
    {
        Stage stage = (Stage) anyNode.getScene().getWindow();

        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/studentclient/gui/view/LoginWindow.fxml"));
            Parent root = fxLoader.load();

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("EASV - Student");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Makes the Menubar look nice on Mac. if it is a Mac the Menubar is placed
     * on the where top of the screen where it normally is placed on a Mac.
     *
     * @param menubar
     * @param mainPane
     */
    public void changeMenubarForMac(MenuBar menubar, AnchorPane mainPane)
    {
        if (System.getProperty("os.name").startsWith("Mac"))
        {
            menubar.useSystemMenuBarProperty().set(true);
            menubar.setMinHeight(0.0);
            menubar.setPrefHeight(0.0);
            menubar.setMaxHeight(0.0);
            mainPane.setPadding(new Insets(-25, 0, 0, 0));
        }
    }
}
