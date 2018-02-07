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
import javafx.stage.Stage;
import studentclient.be.ClassRoom;
import studentclient.be.Course;
import studentclient.be.ScheduleItem;
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
            courses.add(new Course("SDE"));
            courses.add(new Course("SCO"));
            courses.add(new Course("ITO"));
            courses.add(new Course("DBOS"));

            List<String> teachers = new ArrayList<>();
            teachers.add("Peter");
            teachers.add("Mads");
            teachers.add("Lars");
            teachers.add("Bent");

            List<SchoolClass> schoolClasses = new ArrayList<>();
            schoolClasses.add(new SchoolClass("CS2017A"));

            List<ClassRoom> classRooms = new ArrayList<>();
            classRooms.add(new ClassRoom("C3"));

            List<ScheduleItem> scheduleItems = new ArrayList<>();
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 360, 450));
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 540, 580));
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 600, 780));
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 540, 710));
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 360, 540));
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 600, 780));
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.FRIDAY, 900, 1020));
            cont.updateSchedule(scheduleItems);
            scheduleItems.clear();
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 540, 645));
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "Read chapters 1-6", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 645, 810));
            scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 540, 690));
            scheduleItems.add(new ScheduleItem(courses.get(2), teachers.get(2), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 720, 915));
            scheduleItems.add(new ScheduleItem(courses.get(3), teachers.get(3), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 540, 765));
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.THURSDAY, 540, 765));
            scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), null, schoolClasses.get(0), classRooms.get(0), ScheduleDay.FRIDAY, 540, 765));
            cont.updateSchedule(scheduleItems);

            Scene scene = new Scene(root);
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}
