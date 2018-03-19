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
            Student approvedUser = bll.login(username.getText(), encryptedPassword);
            
            
            Stage stage = (Stage) username.getScene().getWindow();

            try
            {
                FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/studentclient/gui/view/MainWindowView.fxml"));
                Parent root = fxLoader.load();

                MainWindowViewController cont = fxLoader.getController();

                cont.setUser(approvedUser);
                cont.updateSchedule(bll.getScheduleItems());

                Scene scene = new Scene(root);
                stage.setResizable(true);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Logged in as " + approvedUser.getName());
                stage.show();
            }catch (IOException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        } catch (BLLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        
    }

}
