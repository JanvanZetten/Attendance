/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.controller;

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
import teacherclient.gui.model.LoginModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class LoginController implements Initializable
{

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PaswordField;
    private LoginModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new LoginModel();
    }

    /**
     * Handles the button to log in.
     * @param event 
     */
    @FXML
    private void handleLogin(ActionEvent event)
    {
        model.handleLogin(UsernameField, PaswordField);
    }

}
