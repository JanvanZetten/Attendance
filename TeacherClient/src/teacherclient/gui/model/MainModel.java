/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.io.IOException;
import teacherclient.be.SchoolClass;
import teacherclient.data.HBoxCell;
import teacherclient.data.CurrentData;
import teacherclient.data.MockData;
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
import teacherclient.gui.controller.ScheduleViewController;

/**
 *
 * @author alexl
 */
public class MainModel
{

    private MockData mData;
    private static CurrentData instance;
    private CurrentData cData;

    public void createMockData()
    {
        mData = new MockData();
        mData.createMockData();
        currentData();
        cData = instance;
    }

    public void setClassList(ListView<HBoxCell> listviewClasses)
    {
        List<HBoxCell> tbl = new ArrayList<>();
        List<SchoolClass> classes = mData.getListAllClasses();

        for (int i = 0; i < classes.size(); i++)
        {
            tbl.add(new HBoxCell(classes.get(i).getName(), classes.get(i), cData, mData));
        }

        ObservableList<HBoxCell> ol = FXCollections.observableArrayList();
        ol.addAll(tbl);
        listviewClasses.setItems(ol);
    }

    private CurrentData currentData()
    {
        if (instance == null)
        {
            instance = new CurrentData();
        }
        return instance;
    }

    public void openSchedule()
    {
        try
        {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/ScheduleView.fxml"));
            Parent root = fxLoader.load();

            ScheduleViewController cont = fxLoader.getController();

            mData.createMockData();
            cont.updateSchedule(mData.getSchedueleItemsTeacher());

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
