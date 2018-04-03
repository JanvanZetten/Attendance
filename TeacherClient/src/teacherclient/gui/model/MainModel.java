/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.io.File;
import java.io.IOException;
import sharedclasses.be.SchoolClass;
import teacherclient.dal.HBoxCell;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sharedclasses.be.Teacher;
import teacherclient.bll.BllManager;
import teacherclient.gui.controller.LoginWindowController;

/**
 *
 * @author alexl
 */
public class MainModel
{

    private BllManager bll;
    private Teacher activeUser;

    /**
     * Sets data class instances to be the same as other classes and sets the
     * data in the view.
     */
    public void createMockData()
    {
        bll = new BllManager();
    }

    /**
     * Sets a list of courses with relevant Absence and Schedule buttons in the
     * same list.
     * @param listviewClasses
     */
    public void setClassList(ListView<HBoxCell> listviewClasses)
    {
        if (activeUser != null)
        {
            List<HBoxCell> tbl = new ArrayList<>();
            List<SchoolClass> classes = activeUser.getClasses();

            for (int i = 0; i < classes.size(); i++)
            {
                tbl.add(new HBoxCell(classes.get(i).getName(), classes.get(i), bll));
            }

            ObservableList<HBoxCell> ol = FXCollections.observableArrayList();
            ol.addAll(tbl);
            listviewClasses.setItems(ol);
        }
        else
        {
            System.out.println("ActiveUser is null");
        }
    }

    public void setActiveUser(Teacher teacher)
    {
        this.activeUser = teacher;
    }

    public void changeMenubarForMac(MenuBar menubar, AnchorPane mainPane)
    {
        if (System.getProperty("os.name").startsWith("Mac"))
        {
            menubar.useSystemMenuBarProperty().set(true);
            menubar.setMinHeight(0.0);
            menubar.setPrefHeight(0.0);
            menubar.setMaxHeight(0.0);
            mainPane.setPadding(new Insets(-25, 0, 0, 0));
        }
    }

    public void logOut(Node anyNode)
    {
        Stage stage = (Stage) anyNode.getScene().getWindow();

        try
        {
            File file = new File("../SharedClasses/src/sharedclasses/gui/view/LoginWindow.fxml");
            System.out.println(file.getCanonicalFile().toURI().toString());
            FXMLLoader fxLoader = new FXMLLoader(file.getCanonicalFile().toURI().toURL());
            fxLoader.setController(new LoginWindowController());
            Parent root = fxLoader.load();

            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("EASV - Student");
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
