/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sharedclasses.dal.UserPropertiesDAO;
import sharedclasses.gui.controller.LoginController;
import teacherclient.gui.model.LoginWindowModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class LoginWindowController extends LoginController
{
    LoginWindowModel lwm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lwm = new LoginWindowModel();

        try
        {
            UserPropertiesDAO.loadAutoLogin();
            String username = UserPropertiesDAO.getUsername();
            String password = UserPropertiesDAO.getPassword();
            if (username != null && password != null)
            {
                usernameField.setText(username);
                chckRemberme.setSelected(true);
                Platform.runLater(() ->
                {
                    lwm.login(username, password, usernameField);
                });
            }
        }
        catch (IOException ex)
        {
            System.out.println("Could not find auto login!");
        }
    }

    @Override
    @FXML
    public void handleLogin(ActionEvent event)
    {
        lwm.handleLogin(usernameField, passwordField, chckRemberme.isSelected());
    }

}
