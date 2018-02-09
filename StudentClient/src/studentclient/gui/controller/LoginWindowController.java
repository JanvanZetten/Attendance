/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.io.IOException;
import java.net.URL;
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
import studentclient.bll.BllManager;

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

    private BllManager bll = new BllManager();

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

            cont.updateSchedule(bll.getScheduleItems());

            Scene scene = new Scene(root);
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}
