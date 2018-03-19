/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sharedclasses.be.Student;
import sharedclasses.bll.BLLException;
import sharedclasses.bll.Encrypter;
import studentclient.bll.BllManager;
import studentclient.gui.controller.MainWindowViewController;

/**
 *
 * @author Asbamz
 */
public class LoginWindowModel
{

    private BllManager bll = new BllManager();

    public void handleLogin(TextField username, PasswordField password)
    {

        //if the content is wrong
        if (username.getText().isEmpty() || password.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid user and password", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = Encrypter.encrypt(password.getText());
        } catch (BLLException ex) {
            Logger.getLogger(LoginWindowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            bll.login(username.getText(), encryptedPassword);
        } catch (BLLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        
    }

}
