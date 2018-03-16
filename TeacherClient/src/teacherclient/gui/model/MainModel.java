/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.io.IOException;
import sharedclasses.be.SchoolClass;
import teacherclient.dal.HBoxCell;
import teacherclient.dal.CurrentData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teacherclient.bll.BllManager;
import teacherclient.gui.controller.ScheduleViewController;

/**
 *
 * @author alexl
 */
public class MainModel
{

    private BllManager bll;
    private CurrentData cData;

    /**
     * Sets data class instances to be the same as other classes and sets the
     * data in the view.
     */
    public void createMockData()
    {
        bll = new BllManager();
        cData = new CurrentData();
    }

    /**
     * Sets a list of courses with relevant Absence and Schedule buttons in the
     * same list.
     * @param listviewClasses
     */
    public void setClassList(ListView<HBoxCell> listviewClasses)
    {
        List<HBoxCell> tbl = new ArrayList<>();
        List<SchoolClass> classes = bll.getListAllClasses();

        for (int i = 0; i < classes.size(); i++)
        {
            tbl.add(new HBoxCell(classes.get(i).getName(), classes.get(i), cData, bll));
        }

        ObservableList<HBoxCell> ol = FXCollections.observableArrayList();
        ol.addAll(tbl);
        listviewClasses.setItems(ol);
    }

    /**
     * Opens a schedule which only shows classes for the logged in teacher.
     */
    public void openSchedule()
    {
        try
        {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/ScheduleView.fxml"));
            Parent root = fxLoader.load();

            ScheduleViewController cont = fxLoader.getController();

            cont.updateSchedule(bll.getSchedueleItemsTeacher());

            Scene scene = new Scene(root);
            newStage.setTitle("Schedule for Teacher");
            newStage.setScene(scene);
            newStage.showAndWait();
        }
        catch (IOException ex)
        {
            Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
