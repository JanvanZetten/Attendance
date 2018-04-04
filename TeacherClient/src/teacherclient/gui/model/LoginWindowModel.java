/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

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
import sharedclasses.be.Teacher;
import sharedclasses.bll.BLLException;
import sharedclasses.bll.Encrypter;
import sharedclasses.dal.UserPropertiesDAO;
import teacherclient.bll.BllManager;
import teacherclient.gui.controller.MainController;

/**
 *
 * @author Asbamz
 */
public class LoginWindowModel
{
    private BllManager bll = new BllManager();

    public void handleLogin(TextField username, PasswordField password, boolean isAutoLogin)
    {

        //if the content is wrong
        if (username.getText().isEmpty() || password.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid user and password", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String encryptedPassword = null;
        try
        {
            encryptedPassword = Encrypter.encrypt(password.getText());
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

        try
        {
            Teacher approvedUser;
            approvedUser = bll.login(username.getText(), encryptedPassword);

            if (isAutoLogin)
            {
                try
                {
                    UserPropertiesDAO.setUsername(approvedUser.getUsername());
                    UserPropertiesDAO.setPassword(encryptedPassword);
                    UserPropertiesDAO.saveAutoLogin();
                }
                catch (IOException ex)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                    alert.showAndWait();
                }
            }

            openWindow(approvedUser, username);
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void login(String username, String password, TextField userfield)
    {
        try
        {
            Teacher approvedUser;
            approvedUser = bll.login(username, password);
            openWindow(approvedUser, userfield);
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void openWindow(Teacher approvedUser, TextField userfield)
    {
        Stage stage = (Stage) userfield.getScene().getWindow();

        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/MainView.fxml"));
            Parent root = fxLoader.load();

            MainController cont = fxLoader.getController();

            cont.setUser(approvedUser);

            Scene scene = new Scene(root);
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("EASV - Teacher Client");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
