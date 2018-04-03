/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.Student;
import sharedclasses.dal.DALException;
import studentclient.gui.model.MainModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainWindowViewController implements Initializable
{
    private final String LOGIN_PRETEXT = "Logged in as ";

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu loginLbl;
    @FXML
    private AnchorPane chartPane;
    @FXML
    private Button btnPresent;

    private MainModel mainModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        mainModel = new MainModel(chartPane);
    }

    /**
     * Sets up the user.
     * @param student
     */
    public void setUser(Student student)
    {
        mainModel.setActiveUser(student);
        loginLbl.setText(LOGIN_PRETEXT + student.getName());
        mainModel.changeMenubarForMac(menuBar, mainAnchorPane);
        try
        {
            mainModel.getPresence(btnPresent);
        }
        catch (DALException ex)
        {
            Logger.getLogger(MainWindowViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlePresent(ActionEvent event)
    {
        mainModel.handlePresent(event);
    }

    @FXML
    private void handleLogOut(ActionEvent event)
    {
        mainModel.logOut(mainAnchorPane);
    }
}
