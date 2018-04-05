/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.io.File;
import sharedclasses.gui.model.PopUpBubble;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sharedclasses.be.Student;
import sharedclasses.bll.BLLException;
import sharedclasses.dal.DALException;
import sharedclasses.gui.model.AbsenceGraph;
import studentclient.bll.BllManager;
import studentclient.gui.controller.LoginWindowController;

/**
 *
 * @author janvanzetten
 */
public class MainModel
{
    private final String TITLE = "Absence Chart";
    private final String X_AXIS_DESCRIPTION = "Absence in percentage";
    private final String Y_AXIS_DESCRIPTION = "Course";
    private final String VALUE_POSTSYMBOL = "%";

    private BllManager bm;
    private Student activeUser;
    private AbsenceGraph ag;
    private AnchorPane chartPane;

    public MainModel(AnchorPane chartPane)
    {
        this.chartPane = chartPane;
        bm = new BllManager();
        setAbsenceGraphData(bm.getChartSeries());
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
            try
            {
                PopUpBubble pub = new PopUpBubble(button, "Presence registred!", Color.web("#54AD32"));
                bm.setPresent(true, activeUser);
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2500),
                        action -> changeButton(button, true)));

                timeline.play();
            }
            catch (BLLException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }
        else if (button.getText().equals("Remove Prescence"))
        {
            try
            {
                PopUpBubble pub = new PopUpBubble(button, "Presence removed!", Color.web("#DB3b26"));
                bm.setPresent(false, activeUser);
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(2500),
                        action -> changeButton(button, false)));

                timeline.play();
            }
            catch (BLLException ex)
            {
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

    public void setAbsenceGraphData(XYChart.Series<String, Number> series)
    {
        ag = new AbsenceGraph(chartPane, series, TITLE, X_AXIS_DESCRIPTION, Y_AXIS_DESCRIPTION, VALUE_POSTSYMBOL);
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
            File file = new File("../SharedClasses/src/sharedclasses/gui/view/LoginWindow.fxml");
            System.out.println(file.getCanonicalFile().toURI().toString());
            FXMLLoader fxLoader = new FXMLLoader(file.getCanonicalFile().toURI().toURL());
            fxLoader.setController(new LoginWindowController());
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

    /**
     * Changes the Presence button depending on whether the user has already
     * pressed present for the day.
     *
     * @param btnPresent
     * @throws DALException
     */
    public void getPresence(Button btnPresent) throws DALException
    {
        changeButton(btnPresent, bm.getPresence(activeUser));
    }
}
