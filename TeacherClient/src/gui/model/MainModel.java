/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Class;
import data.HBoxCell;
import data.CurrentData;
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
    
    private MockData mData;
    private static CurrentData instance;
    private CurrentData cData;

    public void createMockData() {
        mData = new MockData();
        mData.createMockData();
        currentData();
        cData = instance;        
        
        System.out.println(mData.getAlex());
        System.out.println(mData.getAsbjørn());
        System.out.println(mData.getJan());
    }

    public void setClassList(ListView<HBoxCell> listviewClasses) {
        List<HBoxCell> tbl = new ArrayList<>();
        List<Class> classes = mData.getListAllClasses();

        for (int i = 0; i < classes.size(); i++)
        {
            tbl.add(new HBoxCell(classes.get(i).getName(), classes.get(i), cData, mData));
        }

        ObservableList<HBoxCell> ol = FXCollections.observableArrayList();
        ol.addAll(tbl);
        listviewClasses.setItems(ol);
    }

    private CurrentData currentData() {
        if (instance == null) {
            instance = new CurrentData();
        }
        return instance;
    }
    
    
}
