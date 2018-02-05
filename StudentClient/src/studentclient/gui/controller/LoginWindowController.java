/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentclient.be.ClassRoom;
import studentclient.be.Course;
import studentclient.be.ScheduleDay;
import studentclient.be.SchoolClass;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class LoginWindowController implements Initializable
{

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PaswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {

        //if the content is wrong
        if (UsernameField.getText().isEmpty() && PaswordField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid user and password", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        //open the main window
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();

        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/studentclient/gui/view/MainWindowView.fxml"));
            Parent root = fxLoader.load();

            MainWindowViewController cont = fxLoader.getController();

            List<Course> courses = new ArrayList<>();

            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 360, 450));
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 540, 580));
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017B"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 600, 780));
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 540, 710));
            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 360, 540));
            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017B"), new ClassRoom("C3"), ScheduleDay.MONDAY, 600, 780));
            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.FRIDAY, 900, 1020));
            cont.updateSchedule(courses);
            courses.clear();
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 540, 645));
            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 645, 810));
            courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 540, 690));
            courses.add(new Course("ITO", "Lars", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 720, 915));
            courses.add(new Course("DBOS", "Bent", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 540, 765));
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.THURSDAY, 540, 765));
            courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.FRIDAY, 540, 765));
            cont.updateSchedule(courses);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException ex)
        {
            Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}
