/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Student;
import be.Class;
import be.HBoxCell;
import data.MockData;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author alexl
 */
public class MainModel {
    
    private MockData data;

    public void createMockData() {
        data = new MockData();
        data.createMockData();
        
        System.out.println(data.getAlex());
        System.out.println(data.getAsbjørn());
        System.out.println(data.getJan());
    }

    public void setClassList(ListView<HBoxCell> listviewClasses) {
        List<HBoxCell> tbl = new ArrayList<>();
        List<Class> classes = data.getListAllClasses();

        for (int i = 0; i < classes.size(); i++)
        {
            tbl.add(new HBoxCell(classes.get(i).getName(), "Absence", "  ", "Schedule"));
        }

        ObservableList<HBoxCell> ol = FXCollections.observableArrayList();
        ol.addAll(tbl);
        listviewClasses.setItems(ol);
    }
    
}
