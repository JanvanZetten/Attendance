/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import sharedclasses.gui.controller.LoginController;
import studentclient.gui.model.LoginWindowModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class LoginWindowController extends LoginController
{
    LoginWindowModel lwm = new LoginWindowModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @Override
    public void handleLogin(ActionEvent event)
    {
        lwm.handleLogin(UsernameField, PaswordField);
        lwm.rememberMe(UsernameField, PaswordField, chckRemberme);
    }

}
